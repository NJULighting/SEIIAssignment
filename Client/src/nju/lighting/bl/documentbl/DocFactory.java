package nju.lighting.bl.documentbl;

import nju.lighting.bl.documentbl.accountiodoc.AccountInDoc;
import nju.lighting.bl.documentbl.accountiodoc.AccountOutDoc;
import nju.lighting.bl.documentbl.alertdoc.AlertDoc;
import nju.lighting.bl.documentbl.costdoc.CostDoc;
import nju.lighting.bl.documentbl.giftdoc.GiftDoc;
import nju.lighting.bl.documentbl.lossandgaindoc.LossAndGainDoc;
import nju.lighting.bl.documentbl.salesdoc.SalesDoc;
import nju.lighting.bl.documentbl.salesdoc.SalesReturnDoc;
import nju.lighting.bl.documentbl.stockdoc.StockDoc;
import nju.lighting.bl.documentbl.stockdoc.StockReturnDoc;
import nju.lighting.po.doc.DocPO;
import nju.lighting.vo.DocVO;
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
enum DocFactory {
    INSTANT;

    private Map<DocType, Function<DocPO, Doc>> docPOMap;
    private Map<DocType, Function<HistoryDocVO, Doc>> historyDocVOMap;
    private Map<DocType, Function<DocVO, Doc>> docVOMap;

    DocFactory() {
        docPOMap = new HashMap<>();
        docPOMap.put(DocType.ACCOUNT_IN, AccountInDoc::new);
        docPOMap.put(DocType.ACCOUNT_OUT, AccountOutDoc::new);
        docPOMap.put(DocType.COST, CostDoc::new);
        docPOMap.put(DocType.ALERT, AlertDoc::new);
        docPOMap.put(DocType.GIFT, GiftDoc::new);
        docPOMap.put(DocType.LOSS_AND_GAIN, LossAndGainDoc::new);
        docPOMap.put(DocType.SALES, SalesDoc::new);
        docPOMap.put(DocType.SALES_RETURN, SalesReturnDoc::new);
        docPOMap.put(DocType.STOCK, StockDoc::new);
        docPOMap.put(DocType.STOCK_RETURN, StockReturnDoc::new);

        historyDocVOMap = new HashMap<>();
        historyDocVOMap.put(DocType.ACCOUNT_IN, AccountInDoc::new);
        historyDocVOMap.put(DocType.ACCOUNT_OUT, AccountOutDoc::new);
        historyDocVOMap.put(DocType.COST, CostDoc::new);
        historyDocVOMap.put(DocType.ALERT, AlertDoc::new);
        historyDocVOMap.put(DocType.GIFT, GiftDoc::new);
        historyDocVOMap.put(DocType.LOSS_AND_GAIN, LossAndGainDoc::new);
        historyDocVOMap.put(DocType.SALES, SalesDoc::new);
        historyDocVOMap.put(DocType.SALES_RETURN, SalesReturnDoc::new);
        historyDocVOMap.put(DocType.STOCK, StockDoc::new);
        historyDocVOMap.put(DocType.STOCK_RETURN, StockReturnDoc::new);

        docVOMap = new HashMap<>();
        docVOMap.put(DocType.ACCOUNT_IN, AccountInDoc::new);
        docVOMap.put(DocType.ACCOUNT_OUT, AccountOutDoc::new);
        docVOMap.put(DocType.COST, CostDoc::new);
        docVOMap.put(DocType.GIFT, GiftDoc::new);
        docVOMap.put(DocType.LOSS_AND_GAIN, LossAndGainDoc::new);
        docVOMap.put(DocType.SALES, SalesDoc::new);
        docVOMap.put(DocType.SALES_RETURN, SalesReturnDoc::new);
        docVOMap.put(DocType.STOCK, StockDoc::new);
        docVOMap.put(DocType.STOCK_RETURN, StockReturnDoc::new);
    }

    Doc poToDoc(DocPO po) {
        return docPOMap.get(po.getDocType()).apply(po);
    }

    Doc historyDocVOToDoc(HistoryDocVO historyDocVO) {
        DocType docVOType = historyDocVO.getDocVO().getType();
        return historyDocVOMap.get(docVOType).apply(historyDocVO);
    }

    Doc voToDoc(DocVO vo) {
        return docVOMap.get(vo.getType()).apply(vo);
    }
}
