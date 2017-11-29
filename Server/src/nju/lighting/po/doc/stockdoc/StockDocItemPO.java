package nju.lighting.po.doc.stockdoc;

import javax.persistence.*;

/**
 * LastEditTime: 2017/11/7
 * Description:
 * @author GaoMengting
 */
@Entity
@Table(name = "STOCK_DOC_ITEM")
public class StockDocItemPO {

    private int id;

    private String SalesDocID;

    private String commodityID;

    private int number = 0;

    private double totalAmount = 0;

    private String remarks = null;

    public StockDocItemPO() {

    }

    public StockDocItemPO(int id, String SalesDocID, String commodityID,
                          int number, double totalAmount, String remarks) {
        this.id = id;
        this.SalesDocID = SalesDocID;
        this.commodityID = commodityID;
        this.number = number;
        this.totalAmount = totalAmount;
        this.remarks = remarks;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "STOCK_DOC_ID", nullable = false, length = 36)
    public String getSalesDocID() {
        return SalesDocID;
    }

    public void setSalesDocID(String salesDocID) {
        this.SalesDocID = salesDocID;
    }

    @Column(name = "COMMODITY_ID", nullable = false, length = 36)
    public String getCommodityID() {
        return commodityID;
    }

    public void setCommodityID(String commodityID) {
        this.commodityID = commodityID;
    }

    @Column(name = "COUNT", nullable = false)
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Column(name = "TOTAL_AMOUNT", nullable = false)
    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Column(name = "REMARKS", length = 300)
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

}
