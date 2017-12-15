package nju.lighting.po.doc.salesdoc;

import java.io.Serializable;

/**
 * LastEditTime: 2017/11/7
 * Description:
 * @author GaoMengting
 */
public class SalesDocItemPO implements Serializable {

    private static final long serialVersionUID = -6523508821221460968L;
    private int id;
    private String docId;
    private String commodityID;
    private int number = 0;
    private double totalAmount = 0;
    private String remarks;

    /**
     * Constructor for committing a new document.
     */
    public SalesDocItemPO(String commodityID, int number, double totalAmount, String remarks) {
        this.commodityID = commodityID;
        this.number = number;
        this.totalAmount = totalAmount;
        this.remarks = remarks;
    }

    /**
     * Constructor for approval module.
     */
    public SalesDocItemPO(int id, String docId, String commodityID, int number, double totalAmount, String remarks) {
        this.id = id;
        this.docId = docId;
        this.commodityID = commodityID;
        this.number = number;
        this.totalAmount = totalAmount;
        this.remarks = remarks;
    }

    public int getId() {
        return id;
    }

    public String getDocId() {
        return docId;
    }

    public String getCommodityID() {
        return commodityID;
    }

    public int getNumber() {
        return number;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getRemarks() {
        return remarks;
    }
}