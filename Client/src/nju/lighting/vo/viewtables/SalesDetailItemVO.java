package nju.lighting.vo.viewtables;

import nju.lighting.vo.DocVO;

import java.util.Date;

/**
 * Created on 2017/10/21.
 * Description
 * @author 陈俊宇
 */
public class SalesDetailItemVO {
    private Date date;

    private String commodityName;

    private String commodityType;

    private int number;

    private double salePrice;

    private double totalAmount;

    public SalesDetailItemVO(Date date, String commodityName, String commodityType, int number, double salePrice, double totalAmount) {
        this.date = date;
        this.commodityName = commodityName;
        this.commodityType = commodityType;
        this.number = number;
        this.salePrice = salePrice;
        this.totalAmount = totalAmount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return commodityName;
    }

    public void setName(String name) {
        this.commodityName = name;
    }

    public String getCommodityType() {
        return commodityType;
    }

    public void setCommodityType(String commodityType) {
        this.commodityType = commodityType;
    }

    public int getNumber() {
        return number;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

}
