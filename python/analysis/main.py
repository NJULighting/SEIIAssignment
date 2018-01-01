# coding=utf-8
import numpy as np
import data
import matplotlib.pyplot as plt
from keras.models import Sequential
from keras.layers import Dense
from keras.layers import LSTM
from keras.models import load_model
from sklearn.preprocessing import MinMaxScaler
from datetime import date, timedelta
import pickle

look_back = 10 # 用最近30天数据进行预测
append = 30 # 预测接下来30天的走势
scaler_path = "scaler.obj"
model_path = "model.h5"

# 预处理从数据库中提取出来的数据
def _pre_process(init_price, records, start, end):
    length = end.toordinal() - start.toordinal() + 1
    p_records = [[0, 0] for x in range(length)]
    for record in records:
        ord = record[3].toordinal() - start.toordinal()
        if ord < 0 or ord >= length:
            continue
        p_records[ord][0] += record[1]
        p_records[ord][1] += record[2]
    p_records.append([0, init_price])
    for i in range(length):
        if p_records[i][1] == 0: # 平滑化
            p_records[i][1] = p_records[i - 1][1]
        else:
            p_records[i][1] /= p_records[i][0]
    p_records.pop()
    return np.array(p_records)


# 根据最近一段时间来构造训练集/预测集
def create_dataset(dataset, period=182):
    dataX, dataY = [], []
    for k in range(len(dataset) / period):
        for i in range(k * period, (k + 1) * period - look_back - 1):
            dataX.append(dataset[i:(i + look_back), :])
            dataY.append(dataset[i + look_back, :])
    return np.array(dataX), np.array(dataY)


# 构造模型并且持久化
def raw_analysis(start, end):

    # 数据预处理
    length = end.toordinal() - start.toordinal() + 1
    commodities = data.load_commodity()
    Xs = []
    for commodity in commodities:
        c_id = commodity[0]
        i_price = commodity[5]
        records = data.load_sell_record(c_id)
        records = _pre_process(i_price, records, start, end)
        Xs.append(records)
    X = None
    for x in Xs:
        if X is None:
            X = x
        else:
            X = np.append(X, x, axis=0)

    # 正规化
    scaler = MinMaxScaler(feature_range=(0, 1))
    raw_X = scaler.fit_transform(X)
    trainX, trainY = create_dataset(raw_X, length)
    # 很简单的一个RNN
    model = Sequential()
    model.add(LSTM(64, input_shape=(look_back, 2)))
    model.add(Dense(2))
    model.compile(loss='mean_squared_error', optimizer='adam')

    model.fit(trainX, trainY, epochs=10, batch_size=32, verbose=2)
    model.save(model_path)
    with open(scaler_path, 'wb') as f:
        pickle.dump(scaler, f)


# 加载已经存在的模型
def load_exist_model():
    with open("scaler.obj") as f:
        scaler = pickle.load(f)
    model = load_model("model.h5")
    return scaler, model


# 写的无比烂的两个方法
def get_predict_data(scaler, c_id, init_price, start, end):
    raw = data.load_sell_record(c_id)
    records = _pre_process(init_price, raw, start, end)
    records = scaler.fit_transform(records)
    length = end.toordinal() - start.toordinal() + 1
    X, y = create_dataset(records, period=length)
    return X, y


def predict(model, scaler, c_id, init_price, start, end):
    X, y = get_predict_data(scaler, c_id, init_price, start, end)
    in_predict = model.predict(X)
    for i in range(append):
        next = in_predict[-look_back:, :]
        next = next[np.newaxis, :, :]
        result = model.predict(next) * 0.98 # 蜜汁变动 发现预测数据会大幅度上扬，很奇怪，加个系数抑制下过快上涨的趋势
        in_predict = np.append(in_predict, result, axis=0)
    in_predict = scaler.inverse_transform(in_predict)
    return in_predict


def main(start, end):
    commodities = data.load_commodity()
    scaler, model = load_exist_model()
    result_list = []
    td = timedelta(append - 1)
    for commodity in commodities:
        c_id = commodity[0]
        c_price = commodity[5]
        predicts = predict(model, scaler, c_id, c_price, start, end)
        dataset = data.load_sell_record(c_id)
        raw_p = _pre_process(c_price, dataset, start, end + td)
        raw_p[-append:, :] = np.nan
        pred_p = np.empty_like(raw_p)
        pred_p[:, :] = np.nan
        pred_p[look_back:, :] = predicts
        result_list.append((c_id, raw_p, pred_p))
    return result_list

if __name__ == '__main__':
    s = main(date(2018, 1, 1), date(2018, 7, 1))
    for i in range(len(s)):
        plt.plot(s[i][1])
        plt.plot(s[i][2])
        plt.savefig("image/%s.jpg" % s[i][0])
        plt.close()
   #raw_analysis(date(2018, 1, 1), date(2018, 7, 1))



