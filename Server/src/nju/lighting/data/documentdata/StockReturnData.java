package nju.lighting.data.documentdata;

import nju.lighting.po.doc.stockdoc.StockReturnDocPO;

/**
 * Created on 2017/11/28.
 * Description:
 *
 * @author iznauy
 */
public class StockReturnData extends AbstractDocData<StockReturnDocPO> {

    private String docName = "JHTHD";

    public StockReturnData() {
        super(StockReturnDocPO.class);
    }

    @Override
    protected String getDocName() {
        return docName;
    }
}
