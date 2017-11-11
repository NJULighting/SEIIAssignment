package nju.lighting.po.doc.salesdoc;

import nju.lighting.po.doc.DocPO;
import shared.DocType;

import java.util.Date;

public class SalesTypeDocPO extends DocPO {

    private String salesTypeDocID;
    private String customerId;
    private String repository;
    private String userId;
    private String remarks;
    private double discount = 0;
    private double voucher = 0;
    private double finalAmount = 0;

    public SalesTypeDocPO(String id, DocType docType, String userId, Date time, String salesTypeDocID,
                          String customerId, String repository, String userId1,
                          String remarks, double discount, double voucher, double finalAmount) {
        super(id, docType, userId, time);
        this.salesTypeDocID = salesTypeDocID;
        this.customerId = customerId;
        this.repository = repository;
        this.userId = userId1;
        this.remarks = remarks;
        this.discount = discount;
        this.voucher = voucher;
        this.finalAmount = finalAmount;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSalesTypeDocID() {
        return salesTypeDocID;
    }

    public void setSalesTypeDocID(String salesTypeDocID) {
        this.salesTypeDocID = salesTypeDocID;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getRepository() {
        return repository;
    }

    public void setRepository(String repository) {
        this.repository = repository;
    }

    public String getUserID() {
        return userId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getVoucher() {
        return voucher;
    }

    public void setVoucher(double voucher) {
        this.voucher = voucher;
    }

    public double getFinalAmount() {
        return finalAmount;
    }

    public void setFinalAmount(double finalAmount) {
        this.finalAmount = finalAmount;
    }
}
