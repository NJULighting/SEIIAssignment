package nju.lighting.data.docdata;

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
public abstract class AbstractDocData {

    private DocCounter docCounter;

    protected AbstractDocData(String className) {
        docCounter = new DocCounter(className);
    }

    public abstract TwoTuple<ResultMessage, String> commitDoc(DocPO doc);

    public abstract ResultMessage updateDoc(DocPO doc);

    public abstract List<DocPO> getAll();

    public abstract List<DocPO> getByTime(Date from, Date to);

    public abstract List<DocPO> findByUserId(String id);

    public abstract List<DocPO> findByState(DocState docState);

    protected abstract ResultMessage deleteDoc(DocPO doc);

    protected String getId() {
        return null;
    }

}
