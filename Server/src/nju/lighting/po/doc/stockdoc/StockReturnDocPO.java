package nju.lighting.po.doc.stockdoc;

import nju.lighting.po.doc.DocPO;
import shared.DocState;
import shared.DocType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "STOCK_RETURN_DOC")
public class StockReturnDocPO extends DocPO {

    private String customerId;

    private String repository;

    private String remarks;

    private double totalAmount = 0;

    private List<StockDocItemPO> itemPOS;

    public StockReturnDocPO() {

    }

    public StockReturnDocPO(String id, DocType docType, String userId,
                      Date createTime, String customerId,
                      String repository, String remarks, double totalAmount,
                      List<StockDocItemPO> itemPOS) {
        super(id, docType, userId, createTime);
        this.customerId = customerId;
        this.repository = repository;
        this.remarks = remarks;
        this.totalAmount = totalAmount;
        this.itemPOS = itemPOS;
    }

    public StockReturnDocPO(String id, DocType docType, String userId,
                      Date createTime, Date checkTime,
                      String approvalComment, DocState state,
                      String approvalId, String customerId,
                      String repository, String remarks, double totalAmount,
                      List<StockDocItemPO> itemPOS) {
        super(id, docType, userId, createTime, checkTime,
                approvalComment, state, approvalId);
        this.customerId = customerId;
        this.repository = repository;
        this.remarks = remarks;
        this.totalAmount = totalAmount;
        this.itemPOS = itemPOS;
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

    @Column(name = "TOTAL_AMOUNT", nullable = false)
    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Transient
    public List<StockDocItemPO> getItemPOS() {
        return itemPOS;
    }

    public void setItemPOS(List<StockDocItemPO> itemPOS) {
        this.itemPOS = itemPOS;
    }

    @Override
    @Transient
    public List<Object> getItems() {
        List<Object> list = new ArrayList<>();
        list.addAll(this.itemPOS);
        return list;
    }

    @Override
    public void setItems(List<Object> list) {
        this.itemPOS = new ArrayList<>();
        for (Object o: list) {
            itemPOS.add((StockDocItemPO) o);
        }
    }

}
