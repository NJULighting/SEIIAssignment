package nju.lighting.data.documentdata;

import nju.lighting.po.doc.salesdoc.SalesReturnDocPO;

/**
 * Created on 2017/11/28.
 * Description:
 *
 * @author iznauy
 */
public class SaleReturnDocData extends AbstractDocData<SalesReturnDocPO> {

    private String docName = "XSTHD";

    public SaleReturnDocData() {
        super(SalesReturnDocPO.class);
    }

    @Override
    protected String getDocName() {
        return docName;
    }
}
