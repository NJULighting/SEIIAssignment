package nju.lighting.po.doc.stockdoc;

import nju.lighting.po.doc.DocPO;
import shared.DocType;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class StockReturnDocPO extends DocPO implements Serializable{

    private static final long serialVersionUID = -606497910496800338L;
    private String customerId;
    private String repository;
    private String remarks;
    private double totalAmount = 0;
    private List<StockDocItemPO> itemPOS;

    public StockReturnDocPO(String id, DocType docType, String userId, Date time, String customerId
            , String repository, String remarks, double totalAmount, List<StockDocItemPO> itemPOS) {
        super(id, docType, userId, time);
        this.customerId = customerId;
        this.repository = repository;
        this.remarks = remarks;
        this.totalAmount = totalAmount;
        this.itemPOS = itemPOS;
    }

    public List<StockDocItemPO> getItemPOS() {
        return itemPOS;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
