package nju.lighting.po.doc.salesdoc;

import nju.lighting.po.doc.DocPO;
import shared.DocState;
import shared.DocType;

import java.util.Date;
import java.util.List;

public class SalesReturnDocPO extends DocPO {

    private static final long serialVersionUID = 5891779428479239805L;

    private String salesMan;
    private String customerId;
    private String repository;
    private String remarks;
    private double discount = 0;
    private double voucher = 0;
    private double finalAmount = 0;
    private List<SalesDocItemPO> itemPOS;

    /**
     * Constructor for approval module.
     */
    public SalesReturnDocPO(String id, DocType docType, String userId, Date createTime,
                            Date checkTime, String approvalComment, DocState state, String approvalId,
                            String salesMan, String customerId, String repository, String remarks,
                            double discount, double voucher, double finalAmount, List<SalesDocItemPO> itemPOS) {
        super(id, docType, userId, createTime, checkTime, approvalComment, state, approvalId);
        this.salesMan = salesMan;
        this.customerId = customerId;
        this.repository = repository;
        this.remarks = remarks;
        this.discount = discount;
        this.voucher = voucher;
        this.finalAmount = finalAmount;
        this.itemPOS = itemPOS;
    }

    /**
     * Constructor for committing a new document.
     */
    public SalesReturnDocPO(DocType type, String userId, Date createTime, String salesMan,
                            String customerId, String repository, String remarks, double discount,
                            double voucher, double finalAmount, List<SalesDocItemPO> itemPOS) {
        super(type, userId, createTime);
        this.salesMan = salesMan;
        this.customerId = customerId;
        this.repository = repository;
        this.remarks = remarks;
        this.discount = discount;
        this.voucher = voucher;
        this.finalAmount = finalAmount;
        this.itemPOS = itemPOS;
    }

    public String getSalesMan() {
        return salesMan;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getRepository() {
        return repository;
    }

    public String getRemarks() {
        return remarks;
    }

    public double getDiscount() {
        return discount;
    }

    public double getVoucher() {
        return voucher;
    }

    public double getFinalAmount() {
        return finalAmount;
    }

    public List<SalesDocItemPO> getItemPOS() {
        return itemPOS;
    }
}
