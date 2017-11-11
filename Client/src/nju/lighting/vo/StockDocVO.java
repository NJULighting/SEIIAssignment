package nju.lighting.vo;

import shared.DocType;

import java.util.Date;

public class StockDocVO extends StockTypeDocVO {
    public StockDocVO(Date time, String creatorId, String docId, DocType type,
                      String stockTypeDocID, String customerId, String repository,
                      String remarks, double totalAmount) {
        super(time, creatorId, docId, type, stockTypeDocID, customerId, repository, remarks, totalAmount);
    }
}
