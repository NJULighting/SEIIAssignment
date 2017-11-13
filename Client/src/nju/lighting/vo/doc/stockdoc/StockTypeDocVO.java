package nju.lighting.vo.doc.stockdoc;

import nju.lighting.vo.DocVO;
import shared.DocType;

import java.util.Date;

public class StockTypeDocVO extends DocVO {

    private String stockTypeDocID;
    private String customerId;
    private String repository;
    private String remarks = "";
    private double totalAmount = 0;

    public StockTypeDocVO(Date time, String creatorId, String docId, DocType type,
                          String stockTypeDocID, String customerId, String repository,
                          String remarks, double totalAmount) {
        super(time, creatorId, docId, type);
        this.stockTypeDocID = stockTypeDocID;
        this.customerId = customerId;
        this.repository = repository;
        this.remarks = remarks;
        this.totalAmount = totalAmount;
    }

    public String getStockTypeDocID() {
        return stockTypeDocID;
    }

    public void setStockTypeDocID(String stockTypeDocID) {
        this.stockTypeDocID = stockTypeDocID;
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
