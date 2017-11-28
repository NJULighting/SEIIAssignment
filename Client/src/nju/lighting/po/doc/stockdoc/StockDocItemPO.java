package nju.lighting.po.doc.stockdoc;

/**
 * LastEditTime: 2017/11/7
 * Description:
 * @author GaoMengting
 */
public class StockDocItemPO {

    private int id;
    private String SalesDocID;
    private String commodityID;
    private int number = 0;
    private double totalAmount = 0;
    private String remarks = "";

    public StockDocItemPO(int id, String SalesDocID, String commodityID,
                          int number, double totalAmount, String remarks) {
        this.id = id;
        this.SalesDocID = SalesDocID;
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

    public String getSalesDocID() {
        return SalesDocID;
    }

    public void setSalesDocID(String salesDocID) {
        this.SalesDocID = salesDocID;
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
