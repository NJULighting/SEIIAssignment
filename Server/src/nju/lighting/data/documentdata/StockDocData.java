package nju.lighting.data.documentdata;

import nju.lighting.po.doc.stockdoc.StockDocPO;

/**
 * Created on 2017/11/28.
 * Description:
 *
 * @author iznauy
 */
public class StockDocData extends AbstractDocData<StockDocPO> {

    private String docName = "JHD";

    public StockDocData() {
        super(StockDocPO.class);
    }

    @Override
    protected String getDocName() {
        return docName;
    }
}
