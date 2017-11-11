package nju.lighting.po.doc.stockdoc;

import shared.DocType;

import java.util.Date;

public class StockReturnDocPO extends StockTypeDocPO {
    public StockReturnDocPO(String id, DocType docType, String userId,
                            Date time, String stockTypeDocID, String customerId,
                            String repository, String remarks, double totalAmount) {
        super(id, docType, userId, time, stockTypeDocID, customerId, repository, remarks, totalAmount);
    }
}
