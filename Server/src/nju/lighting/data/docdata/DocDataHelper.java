package nju.lighting.data.docdata;

import nju.lighting.data.utils.CommonOperation;
import nju.lighting.po.doc.DocPO;
import shared.DocState;
import shared.ResultMessage;
import shared.TwoTuple;

import java.util.Date;
import java.util.List;

/**
 * Created on 2017/11/29.
 * Description:
 *
 * @author iznauy
 */
public class DocDataHelper<T, R> {

    private CommonOperation<T> docOperation;

    private CommonOperation<R> docItemOperation;

    public DocDataHelper(String className1, String className2) {
        docOperation = new CommonOperation<>(className1);
        docItemOperation = new CommonOperation<>(className2);
    }

    public TwoTuple<ResultMessage, String> commitDoc(T doc) {
        ResultMessage docResultMessage = docOperation.add(doc);

        return null;
    }

    public ResultMessage updateDoc(T doc) {
        return null;
    }

    public List<T> getAll() {
        List<T> docPOS = docOperation.getAll();
        return null;
    }

    public List<T> getByTime(Date from, Date to) {
        return null;
    }

    public List<T> findByUserId(String id) {
        return null;
    }

    public List<T> findByState(DocState docState) {
        return null;
    }

    public ResultMessage deleteDoc(T doc) {
        return null;
    }
}
