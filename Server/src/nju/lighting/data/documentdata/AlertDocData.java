package nju.lighting.data.documentdata;

import nju.lighting.po.doc.alertdoc.AlertDocPO;

/**
 * Created on 2017/11/28.
 * Description:
 *
 * @author iznauy
 */
public class AlertDocData extends AbstractDocData<AlertDocPO> {

    private String docName = "BJD";

    public AlertDocData() {
        super(AlertDocPO.class);
    }

    @Override
    protected String getDocName() {
        return docName;
    }
}
