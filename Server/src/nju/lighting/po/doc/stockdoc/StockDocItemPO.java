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
                          int number, double totalAmount,String remarks){
        this.id = id;
        this.SalesDocID = SalesDocID;
        this.commodityID=commodityID;
        this.number=number;
        this.totalAmount = totalAmount;
        this.remarks=remarks;
    }

    public void setId(int id){ this.id=id; }
    public void setSalesDocID(String salesDocID){ this.SalesDocID = salesDocID; }
    public void setCommodityID(String commodityID) {
        this.commodityID = commodityID;
    }
    public void setNumber(int number){this.number = number;}
    public void setTotalAmount(double totalAmount){this.totalAmount =totalAmount;}
    public void setRemarks(String remarks){
        this.remarks = remarks;
    }


    public int getId(){ return id; }
    public String getSalesDocID(){ return SalesDocID; }
    public String getCommodityID(){ return commodityID; }
    public int getNumber(){return number;}
    public double getTotalAmount(){return totalAmount;}
    public String getRemarks(){return remarks; }

}
