package nju.lighting.bl.commoditybl;

import nju.lighting.vo.commodity.BasicCommodityItemVO;

/**
 * 商品基本信息领域对象模型
 */
public class BasicCommodityItem {

    private String id;
    private String name;
    private int categoryID;
    private int repCount;
    private double recentInPrice;
    private double recentSellPrice;
    private String modelNumber;

    BasicCommodityItem(String id, String name, int categoryID, int repCount,
                       double recentInPrice, double recentSellPrice, String modelNumber) {
        this.id = id;
        this.name = name;
        this.categoryID = categoryID;
        this.repCount = repCount;
        this.recentInPrice = recentInPrice;
        this.recentSellPrice = recentSellPrice;
        this.modelNumber = modelNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public int getRepCount() {
        return repCount;
    }

    public void setRepCount(int repCount) {
        this.repCount = repCount;
    }

    public double getRecentInPrice() {
        return recentInPrice;
    }

    public void setRecentInPrice(double recentInPrice) {
        this.recentInPrice = recentInPrice;
    }

    public double getRecentSellPrice() {
        return recentSellPrice;
    }

    public void setRecentSellPrice(double recentSellPrice) {
        this.recentSellPrice = recentSellPrice;
    }

    public String getModelNumber() {
        return modelNumber;
    }

    BasicCommodityItemVO toVo() {
        return new BasicCommodityItemVO(id, name, repCount, recentInPrice, recentSellPrice, modelNumber);
    }
}
