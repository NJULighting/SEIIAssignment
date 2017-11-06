package nju.lighting.vo;

public class CommodityListItemVO {

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

    public CommodityPO getCommodity() {
        return commodity;
    }

    public void setCommodityID(String commodityID) {
        this.commodityID = commodityID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCommodityType(String commodityType) {
        this.commodityType = commodityType;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getRemarks(){return remarks; }
}
