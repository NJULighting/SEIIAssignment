package nju.lighting.vo;

/**
 * Created on 2017/10/21.
 * Description
 *
 * @author 陈俊宇
 */
public class SaleRecordVO {
    private long date;

    private String commodityName;

    private String commodityType;

    private int number;

    private double salePrice;

    private double totalAmount;

    public long getDate(){return date;}

    public String getName(){return commodityName;}

    public String getCommodityType(){return commodityType;}

    public int getNumber(){return number;}

    public double getSalePrice(){ return salePrice;}

    public double getTotalAmount(){return totalAmount;}

    public void setDate(long date){this.date=date;}

    public void setName(String name) {
        this.commodityName = name;
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

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
