package nju.lighting.po.doc.salesdoc;

import shared.DocType;

import java.util.Date;

public class SalesDocPO extends SalesTypeDocPO {
    public SalesDocPO(String id, DocType docType, String userId, Date time,
                      String salesTypeDocID, String customerId, String repository,
                       String remarks, double discount, double voucher, double finalAmount) {
        super(id, docType, userId, time, salesTypeDocID, customerId, repository, remarks, discount, voucher, finalAmount);
    }
}
