package nju.lighting.data.documentdata;

import nju.lighting.data.utils.CommonOperation;
import nju.lighting.po.doc.DocPO;
import shared.DocState;
import shared.ResultMessage;
import shared.TwoTuple;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created on 2017/11/28.
 * Description:
 *
 * @author iznauy
 */
public abstract class AbstractDocData<T extends DocPO> {

    public static final String TIME_NAME = "time";

    private CommonOperation<T> docDataCommonOperation;

    private DocCounter docCounter;

    public AbstractDocData(Class clss)  {
        docCounter = new DocCounter(clss.getName());
        docDataCommonOperation = new CommonOperation<>(clss.getName());
    }

    public TwoTuple<ResultMessage, String> commitDoc(T doc) {
        Date now = new Date();
        SimpleDateFormat naiveFormat = new SimpleDateFormat("yyyyMMd");
        String id = getDocName() + "-" + naiveFormat.format(now) + "-";
        int count = docCounter.countTodayDoc();
        if (count == -1)
            return new TwoTuple<>(ResultMessage.FAILURE, null);
        id += count;
        doc.setTime(now);
        doc.setId(id);
        ResultMessage resultMessage = docDataCommonOperation.add(doc);
        return new TwoTuple<>(resultMessage, id);
    }

    public ResultMessage updateDoc(T doc) {
        return docDataCommonOperation.update(doc);
    }

    public List<T> getAll() {
        return docDataCommonOperation.getAll();
    }

    public List<T> getByTime(Date from, Date to) {
        return docDataCommonOperation.getDataBetweenTime(from, to, TIME_NAME);
    }

    public List<T> findByUserId(String id) {
        return docDataCommonOperation.getListBySingleField("id", id);
    }

    public List<T> findByState(DocState docState) {
        return docDataCommonOperation.getListBySingleField("state", docState);
    }

    protected abstract String getDocName();


}
