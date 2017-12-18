package nju.lighting.data.docdata;

import nju.lighting.po.doc.DocPO;
import nju.lighting.po.doc.accountiodoc.AccountIODocPO;
import nju.lighting.po.doc.accountiodoc.AccountOutDocPO;
import nju.lighting.po.doc.alertdoc.AlertDocPO;
import nju.lighting.po.doc.costdoc.CostDocPO;
import nju.lighting.po.doc.giftdoc.GiftDocPO;
import nju.lighting.po.doc.lossandgaindoc.LossAndGainDocPO;
import nju.lighting.po.doc.salesdoc.SalesDocPO;
import nju.lighting.po.doc.salesdoc.SalesReturnDocPO;
import nju.lighting.po.doc.stockdoc.StockDocPO;
import nju.lighting.po.doc.stockdoc.StockReturnDocPO;
import shared.DocType;

import java.util.HashMap;
import java.util.List;

/**
 * Created on 2017/12/18.
 * Description:
 *
 * @author iznauy
 */
class DocTypeSetter {

    private static HashMap<String, DocType> nameToType = new HashMap<>();

    static {
        nameToType.put(AlertDocPO.class.getName(), DocType.ALERT);
        nameToType.put(LossAndGainDocPO.class.getName(), DocType.LOSS_AND_GAIN);
        nameToType.put(AccountIODocPO.class.getName(), DocType.ACCOUNT_IN);
        nameToType.put(AccountOutDocPO.class.getName(), DocType.ACCOUNT_OUT);
        nameToType.put(SalesReturnDocPO.class.getName(), DocType.SALES_RETURN);
        nameToType.put(SalesDocPO.class.getName(), DocType.SALES);
        nameToType.put(CostDocPO.class.getName(), DocType.COST);
        nameToType.put(GiftDocPO.class.getName(), DocType.GIFT);
        nameToType.put(StockDocPO.class.getName(), DocType.STOCK);
        nameToType.put(StockReturnDocPO.class.getName(), DocType.STOCK_RETURN);
    }


    private static DocType translate(String className) {
        return nameToType.get(className);
    }

    static DocPO setType(String className, DocPO docPO) {
        if (docPO == null)
            return null;
        DocType type = translate(className);
        docPO.setDocType(type);
        return docPO;
    }

    static List<DocPO> setType(String className, List<DocPO> docPOs) {
        if (docPOs == null)
            return null;
        DocType type = translate(className);
        for (DocPO docPO : docPOs)
            docPO.setDocType(type);
        return docPOs;
    }

}
