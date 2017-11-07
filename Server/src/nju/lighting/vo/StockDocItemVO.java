package nju.lighting.vo;
/**
 * LastEditTime: 2017/11/7
 * Description:
 * @author GaoMengting
 */
public class StockDocItemVO {

    private int id;
    private String stockDocID;
    private String commodityID;
    private String commodityName;
    private String commodityType;
    private int number = 0;
    private double price = 0;
    private double totalAmount = 0;
    private String remarks = "";

    private void updateTotalAmount(){
        totalAmount = number*price;
    }

    public StockDocItemVO(){}
    public StockDocItemVO(int id, String stockDocID, String commodityID, String commodityName, String commodityType,
                          int number, double price, String remarks){
        this.id = id;
        this.stockDocID = stockDocID;
        this.commodityID=commodityID;
        this.commodityName=commodityName;
        this.commodityType=commodityType;
        this.number=number;
        this.price=price;
        this.remarks=remarks;
    }

    public void setId(int id){ this.id=id; }
    public void setSalesDocID(String salesDocID){ this.stockDocID = stockDocID; }
    public void setCommodityID(String commodityID) {
        this.commodityID = commodityID;
    }
    public void setCommodityName(String commodityName){ this.commodityName = commodityName; }
    public void setCommodityType(String commodityType) {
        this.commodityType = commodityType;
    }
    public void setNumber(int number){
        this.number = number;
        updateTotalAmount();
    }
    public void setPrice(double price){
        this.price = price;
        updateTotalAmount();
    }
    public void setRemarks(String remarks){
        this.remarks = remarks;
    }


    public int getId(){ return id; }
    public String getStockDocID(){ return stockDocID; }
    public String getCommodityID(){ return commodityID; }
    public String getCommodityName(){ return commodityName; }
    public String getCommodityType(){ return commodityType;}
    public int getNumber(){return number;}
    public double getPrice(){ return price;}
    public double getTotalAmount(){return totalAmount;}
    public String getRemarks(){return remarks; }

}
