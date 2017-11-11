package nju.lighting.po.doc.salesdoc;

import shared.DocType;

import java.util.Date;

public class SalesReturnDocPO extends SalesTypeDocPO {

    public SalesReturnDocPO(String id, DocType docType, String userId, Date time,
                            String salesTypeDocID, String customerId, String repository,
                            String userId1, String remarks, double discount, double voucher,
                            double finalAmount) {
        super(id, docType, userId, time, salesTypeDocID, customerId, repository, userId1, remarks, discount, voucher, finalAmount);
    }
}
