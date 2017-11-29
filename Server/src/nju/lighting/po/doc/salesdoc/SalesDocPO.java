package nju.lighting.po.doc.salesdoc;

import nju.lighting.po.doc.DocPO;
import shared.DocState;
import shared.DocType;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "SALES_DOC")
public class SalesDocPO extends DocPO {

    private String salesMan;

    private String customerId;

    private String repository;

    private String remarks;

    private double discount = 0;

    private double voucher = 0;

    private double finalAmount = 0;

    private List<SalesDocItemPO> itemPOS;

    public SalesDocPO() {

    }

    public SalesDocPO(String id, DocType docType, String userId,
                      Date createTime, Date checkTime, String approvalComment,
                      DocState state, String approvalId, String salesMan,
                      String customerId, String repository, String remarks,
                      double discount, double voucher, double finalAmount,
                      List<SalesDocItemPO> itemPOS) {
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

    public SalesDocPO(String id, DocType docType,
                      String userId, Date createTime, String salesMan,
                      String customerId, String repository, String remarks,
                      double discount, double voucher, double finalAmount) {
        super(id, docType, userId, createTime);
        this.salesMan = salesMan;
        this.customerId = customerId;
        this.repository = repository;
        this.remarks = remarks;
        this.discount = discount;
        this.voucher = voucher;
        this.finalAmount = finalAmount;
    }

    @Column(name = "CUSTOMER_ID", nullable = false, length = 20)
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @Column(name = "REPOSITORY_ID", length = 5)
    public String getRepository() {
        return repository;
    }

    public void setRepository(String repository) {
        this.repository = repository;
    }

    @Column(name = "REMARKS", length = 300)
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Column(name = "DISCOUNT", nullable = false)
    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Column(name = "VOUCHER")
    public double getVoucher() {
        return voucher;
    }

    public void setVoucher(double voucher) {
        this.voucher = voucher;
    }

    @Column(name = "FINAL_AMOUNT", nullable = false)
    public double getFinalAmount() {
        return finalAmount;
    }

    public void setFinalAmount(double finalAmount) {
        this.finalAmount = finalAmount;
    }

    @Column(name = "SALES_MAN", nullable = false)
    public String getSalesMan() {
        return salesMan;
    }

    public void setSalesMan(String salesMan) {
        this.salesMan = salesMan;
    }

    @Transient
    public List<SalesDocItemPO> getItemPOS() {
        return itemPOS;
    }

    public void setItemPOS(List<SalesDocItemPO> itemPOS) {
        this.itemPOS = itemPOS;
    }
}
