package nju.lighting.data.documentdata;

import nju.lighting.po.doc.salesdoc.SalesDocPO;

/**
 * Created on 2017/11/28.
 * Description:
 *
 * @author iznauy
 */
public class SaleDocData extends AbstractDocData<SalesDocPO> {

    private String docName = "XSD";

    public SaleDocData() {
        super(SalesDocPO.class);
    }

    @Override
    protected String getDocName() {
        return docName;
    }
}
