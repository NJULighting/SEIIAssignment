package nju.lighting.po;

import java.util.ArrayList;

public class CommodityPO {

    private String name;

    private String commodityType;

    private String id;

    private int count;

    private double recentCostPrice;

    private ArrayList<Double> recentTenCostPrice;

    private double recentSalePrice;

    private ArrayList<Double> recentTenSalePrice;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCommodityType() {
        return commodityType;
    }

    public void setCommodityType(String commodityType) {
        this.commodityType = commodityType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getRecentCostPrice() {
        return recentCostPrice;
    }

    public void setRecentCostPrice(double recentCostPrice) {
        this.recentCostPrice = recentCostPrice;
    }

    public ArrayList<Double> getRecentTenCostPrice() {
        return recentTenCostPrice;
    }

    public void setRecentTenCostPrice(ArrayList<Double> recentTenCostPrice) {
        this.recentTenCostPrice = recentTenCostPrice;
    }

    public double getRecentSalePrice() {
        return recentSalePrice;
    }

    public void setRecentSalePrice(double recentSalePrice) {
        this.recentSalePrice = recentSalePrice;
    }

    public ArrayList<Double> getRecentTenSalePrice() {
        return recentTenSalePrice;
    }

    public void setRecentTenSalePrice(ArrayList<Double> recentTenSalePrice) {
        this.recentTenSalePrice = recentTenSalePrice;
    }
}
