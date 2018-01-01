import numpy as np
import data
from keras.models import Model, load_model
from keras.layers import Input, LSTM, Dense
from sklearn.preprocessing import MinMaxScaler
from datetime import *
import matplotlib.pyplot as plt

look_back = 30
append = 30
scaler_path = "n_scaler.obj"
model_path = "n_model.h5"
epochs = 2
batch_size = 32
latent_dim = 256

def _pre_process(init_price, records, start, length):
    p_records = [[0, 0] for x in range(length)]
    for record in records:
        ord = record[3].toordinal() - start
        if ord < 0 or ord >= length:
            continue
        p_records[ord][0] += record[1]
        p_records[ord][1] += record[2]
    p_records.append([0, init_price])
    for i in range(length):
        if p_records[i][1] == 0:
            p_records[i][1] = p_records[i - 1][1]
        else:
            p_records[i][1] /= p_records[i][0]
    p_records.pop()
    return np.array(p_records)


def create_dataset(dataset, period=182):
    dataX, dataY_i, dataY_o = [], [], []
    for k in range(len(dataset) / period):
        for i in range(k * period, (k + 1) * period - look_back - append - 1):
            dataX.append(dataset[i:(i+look_back), :])
            dataY_i.append(dataset[(i+look_back-1):(i+look_back+append-1), :])
            dataY_o.append(dataset[(i+look_back):(i+look_back+append), :])
    return np.array(dataX), np.array(dataY_i), np.array(dataY_o)


def get_predict_data(dataset):
    return dataset[-look_back:, :]


def create_model(start, end):

    length = end.toordinal() - start.toordinal()
    commodities = data.load_commodity()
    Xs = []
    for commodity in commodities:
        c_id = commodity[0]
        i_price = commodity[5]
        records = data.load_sell_record(c_id)
        records = _pre_process(i_price, records, start.toordinal(), length)
        Xs.append(records)

    X = None
    for x in Xs:
        if X is None:
            X = x
        else:
            X = np.append(X, x, axis=0)

    scaler = MinMaxScaler(feature_range=(0, 1))
    raw_X = scaler.fit_transform(X)
    trainX, trainY_i, trainY_o = create_dataset(raw_X, length)

    print "trainX_shape: ", trainX.shape
    print "trainY_i_shape: ", trainY_i.shape
    print "trainY_i_shape: ", trainY_o.shape

    encoder_inputs = Input(shape=(30, 2))
    encoder = LSTM(latent_dim, return_state=True)
    encoder_outputs, state_h, state_c = encoder(encoder_inputs)
    encoder_states = [state_h, state_c]

    decoder_inputs = Input(shape=(30, 2))
    decoder_lstm = LSTM(latent_dim, return_sequences=True, return_state=True)
    decoder_outputs, _, _ = decoder_lstm(decoder_inputs, initial_state=encoder_states)
    decoder_dense = Dense(2, activation='softmax')
    decoder_outputs = decoder_dense(decoder_outputs)

    model = Model([encoder_inputs, decoder_inputs], decoder_outputs)
    model.compile(optimizer='adam', loss='mean_squared_error')
    model.fit([trainX, trainY_i], trainY_o, batch_size=batch_size, epochs=epochs)
    model.save("two_layer.h5")

    encoder_model = Model(encoder_inputs, encoder_states)

    decoder_state_input_h = Input(shape=(latent_dim,))
    decoder_state_input_c = Input(shape=(latent_dim,))
    decoder_states_inputs = [decoder_state_input_h, decoder_state_input_c]
    decoder_outputs, state_h, state_c = decoder_lstm(
        decoder_inputs, initial_state=decoder_states_inputs)
    decoder_states = [state_h, state_c]
    decoder_outputs = decoder_dense(decoder_outputs)
    decoder_model = Model(
        [decoder_inputs] + decoder_states_inputs,
        [decoder_outputs] + decoder_states)

    encoder_model.save("encode.h5")
    decoder_model.save("decode.h5")

    for commodity in commodities:
        c_id = commodity[0]
        i_price = commodity[5]
        records = data.load_sell_record(c_id)
        records = _pre_process(i_price, records, start.toordinal(), length)
        in_data = get_predict_data(records)
        in_data = scaler.fit_transform(in_data)
        in_data = in_data[np.newaxis, :, :]
        state_value = encoder_model.predict(in_data)
        target_seq = in_data[-1:, :, :]
        output, _, _ = decoder_model.predict([target_seq] + state_value)
        result = output
        result = scaler.inverse_transform(result[0])
        records = data.load_sell_record(c_id)
        raw = _pre_process(i_price, records, start.toordinal(), length + append)
        raw[-append:, :] = np.nan
        pred_p = np.empty_like(raw)
        pred_p[:, :] = np.nan
        pred_p[-append:, :] = result
        plt.plot(raw)
        plt.plot(pred_p)
        plt.savefig("image2/%s.jpg" % c_id)
        plt.close()




if __name__=='__main__':
    create_model(date(2018, 1, 1), date(2018, 7, 1))