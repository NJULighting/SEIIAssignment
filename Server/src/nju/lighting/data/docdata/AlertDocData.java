package nju.lighting.data.docdata;

import nju.lighting.data.utils.CommonOperation;
import nju.lighting.po.doc.DocPO;
import nju.lighting.po.doc.alertdoc.AlertDocItemPO;
import nju.lighting.po.doc.alertdoc.AlertDocPO;
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
public class AlertDocData extends AbstractDocData {


    public AlertDocData(String className) {
        super(className);
    }

    @Override
    public TwoTuple<ResultMessage, String> commitDoc(DocPO doc) {
        return null;
    }

    @Override
    public ResultMessage updateDoc(DocPO doc) {
        return null;
    }

    @Override
    public List<DocPO> getAll() {
        return null;
    }

    @Override
    public List<DocPO> getByTime(Date from, Date to) {
        return null;
    }

    @Override
    public List<DocPO> findByUserId(String id) {
        return null;
    }

    @Override
    public List<DocPO> findByState(DocState docState) {
        return null;
    }

    @Override
    protected ResultMessage deleteDoc(DocPO doc) {
        return null;
    }
}
