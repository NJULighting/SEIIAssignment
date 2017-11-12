package nju.lighting.vo.doc.salesdoc;

import shared.DocType;

import java.util.Date;

public class SalesReturnDocVO extends SalesTypeDocVO {

    public SalesReturnDocVO(Date time, String creatorId, String docId,
                            DocType type, String salesTypeDocID, String customerId,
                            String salesman, String repository, String remarks,
                            double beforeDiscountAmount, double discount,
                            double voucher, double finalAmount) {
        super(time, creatorId, docId, type, salesTypeDocID, customerId, salesman, repository, remarks, beforeDiscountAmount, discount, voucher, finalAmount);
    }
}
