package nju.lightingServer.po.doc.stockdoc;

import shared.DocType;

import java.util.Date;

public class StockDocPO extends StockTypeDocPO {
    public StockDocPO(String id, DocType docType, String userId, Date time,
                      String stockTypeDocID, String customerId, String repository,
                       String remarks, double totalAmount) {
        super(id, docType, userId, time, stockTypeDocID, customerId, repository, remarks, totalAmount);
    }
}
