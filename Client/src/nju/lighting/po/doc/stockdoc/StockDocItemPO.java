package nju.lighting.po.doc.stockdoc;

import shared.Item;

import java.io.Serializable;

/**
 * LastEditTime: 2017/11/7
 * Description:
 * @author GaoMengting
 */
public class StockDocItemPO implements Serializable {

    private static final long serialVersionUID = -8422670828085703373L;
    private int id;
    private String docId;
    private String commodityID;
    private int number = 0;
    private double totalAmount = 0;
    private String remarks = null;

    public StockDocItemPO(int id, String SalesDocID, String commodityID,
                          int number, double totalAmount, String remarks) {
        this.id = id;
        this.docId = SalesDocID;
        this.commodityID = commodityID;
        this.number = number;
        this.totalAmount = totalAmount;
        this.remarks = remarks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDocID() {
        return docId;
    }

    public void setDocId(String docID) {
        this.docId = docID;
    }

    public String getCommodityID() {
        return commodityID;
    }

    public void setCommodityID(String commodityID) {
        this.commodityID = commodityID;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
