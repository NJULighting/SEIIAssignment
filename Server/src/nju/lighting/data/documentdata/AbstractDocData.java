package nju.lighting.data.documentdata;

import nju.lighting.data.utils.CommonOperation;
import nju.lighting.po.doc.DocPO;
import shared.DocState;
import shared.ResultMessage;
import shared.TwoTuple;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created on 2017/11/28.
 * Description:
 *
 * @author iznauy
 */
public abstract class AbstractDocData<T extends DocPO> {

    public static final String TIME_NAME = "createTime";

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
        doc.setCreateTime(now);
        doc.setId(id);
        ResultMessage resultMessage = docDataCommonOperation.add(doc);
        return new TwoTuple<>(resultMessage, id);
    }

    public ResultMessage updateDoc(T doc) {
        return docDataCommonOperation.update(doc);
    }

    public List<DocPO> getAll() {
        List<T> list = docDataCommonOperation.getAll();
        List<DocPO> resultList = new ArrayList<>();
        resultList.addAll(list);
        return resultList;
    }

    public List<DocPO> getByTime(Date from, Date to) {
        List<T> list = docDataCommonOperation.getDataBetweenTime(from, to, TIME_NAME);
        List<DocPO> resultList = new ArrayList<>();
        resultList.addAll(list);
        return resultList;
    }

    public List<DocPO> findByUserId(String id) {
        List<T> list = docDataCommonOperation.getListBySingleField("id", id);
        List<DocPO> resultList = new ArrayList<>();
        resultList.addAll(list);
        return resultList;
    }

    public List<DocPO> findByState(DocState docState) {
        List<T> list =  docDataCommonOperation.getListBySingleField("state", docState);
        List<DocPO> resultList = new ArrayList<>();
        resultList.addAll(list);
        return resultList;
    }

    protected abstract String getDocName();


}
