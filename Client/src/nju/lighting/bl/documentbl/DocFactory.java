package nju.lighting.bl.documentbl;

import nju.lighting.bl.documentbl.accountiodoc.AccountIODoc;
import nju.lighting.bl.documentbl.alertdoc.AlertDoc;
import nju.lighting.bl.documentbl.costdoc.CostDoc;
import nju.lighting.bl.documentbl.giftdoc.GiftDoc;
import nju.lighting.bl.documentbl.lossandgaindoc.LossAndGainDoc;
import nju.lighting.bl.documentbl.salesdoc.SalesDoc;
import nju.lighting.bl.documentbl.salesdoc.SalesReturnDoc;
import nju.lighting.bl.documentbl.stockdoc.StockDoc;
import nju.lighting.bl.documentbl.stockdoc.StockReturnDoc;
import nju.lighting.po.doc.DocPO;
import nju.lighting.vo.doc.historydoc.HistoryDocVO;
import shared.DocType;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Created on 2017/12/22.
 * Description: Static Factory Method for creating Doc with
 * HistoryDocVO and DocPO
 * @author Liao
 */
public class DocFactory {
    private Map<DocType, Function<DocPO, Doc>> docPOMap;
    private Map<DocType, Function<HistoryDocVO, Doc>> historyDocVOMap;

    DocFactory() {
        docPOMap = new HashMap<>();
        docPOMap.put(DocType.ACCOUNT_IN, AccountIODoc::new);
        docPOMap.put(DocType.ACCOUNT_OUT, AccountIODoc::new);
        docPOMap.put(DocType.COST, CostDoc::new);
        docPOMap.put(DocType.ALERT, AlertDoc::new);
        docPOMap.put(DocType.GIFT, GiftDoc::new);
        docPOMap.put(DocType.LOSS_AND_GAIN, LossAndGainDoc::new);
        docPOMap.put(DocType.SALES, SalesDoc::new);
        docPOMap.put(DocType.SALES_RETURN, SalesReturnDoc::new);
        docPOMap.put(DocType.STOCK, StockDoc::new);
        docPOMap.put(DocType.STOCK_RETURN, StockReturnDoc::new);

        historyDocVOMap = new HashMap<>();
        historyDocVOMap.put(DocType.ACCOUNT_IN, AccountIODoc::new);
        historyDocVOMap.put(DocType.ACCOUNT_OUT, AccountIODoc::new);
        historyDocVOMap.put(DocType.COST, CostDoc::new);
        historyDocVOMap.put(DocType.ALERT, AlertDoc::new);
        historyDocVOMap.put(DocType.GIFT, GiftDoc::new);
        historyDocVOMap.put(DocType.LOSS_AND_GAIN, LossAndGainDoc::new);
        historyDocVOMap.put(DocType.SALES, SalesDoc::new);
        historyDocVOMap.put(DocType.SALES_RETURN, SalesReturnDoc::new);
        historyDocVOMap.put(DocType.STOCK, StockDoc::new);
        historyDocVOMap.put(DocType.STOCK_RETURN, StockReturnDoc::new);
    }

    public Doc poToDoc(DocPO po) {
        return docPOMap.get(po.getDocType()).apply(po);
    }

    public Doc historyDocVOToDoc(HistoryDocVO historyDocVO) {
        DocType docVOType = historyDocVO.getDocVO().getType();
        return historyDocVOMap.get(docVOType).apply(historyDocVO);
    }
}
