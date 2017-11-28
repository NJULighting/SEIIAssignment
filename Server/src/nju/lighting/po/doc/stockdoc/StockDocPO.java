package nju.lighting.po.doc.stockdoc;

import nju.lighting.po.doc.DocPO;
import shared.DocType;

import java.util.Date;

public class StockDocPO extends DocPO {

    private String stockDocID;

    private String customerId;

    private String repository;

    private String remarks = "";

    private double totalAmount = 0;

    public StockDocPO(String id, DocType docType, String userId, Date time,
                      String stockDocID, String customerId, String repository,
                      String remarks, double totalAmount) {
        super(id, docType, userId, time);
        this.stockDocID = stockDocID;
        this.customerId = customerId;
        this.repository = repository;
        this.remarks = remarks;
        this.totalAmount = totalAmount;
    }

    public String getStockDocID() {
        return stockDocID;
    }

    public void setStockDocID(String stockDocID) {
        this.stockDocID = stockDocID;
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
