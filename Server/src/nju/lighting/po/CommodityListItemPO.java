package nju.lighting.po;

public class CommodityListItemPO {

    private CommodityPO commodity;
    private String commodityID;
    private String name;
    private String commodityType;
    private int number = 0;
    private double salePrice = 0;
    private double totalAmount = 0;
    private String remarks = "";

    public void setCommodity(CommodityPO commodity){
        this.commodity = commodity;
        commodityID = commodity.getId();
        name = commodity.getName();
        commodityType = commodity.getCommodityType();
        salePrice = commodity.getRecentSalePrice();
    }
    public void setNumber(int number){
        this.number = number;
        totalAmount = number*salePrice;
    }
    public void setRemarks(String remarks){
        this.remarks = remarks;
    }

    public String getCommodityID(){ return commodityID;}
    public String getName(){return name;}
    public String getCommodityType(){return commodityType;}
    public int getNumber(){return number;}
    public double getSalePrice(){ return salePrice;}
    public double getTotalAmount(){return totalAmount;}
    public String getRemarks(){return remarks;
    }
}
