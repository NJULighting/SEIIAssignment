package nju.lighting.vo.commodity;

import shared.ICommodityTreeNode;

public class BasicCommodityItemVO implements ICommodityTreeNode {

    private String id;

    private String name;

    private CommodityCategoryVO category;

    private int repCount;

    private double recentInPrice;

    private double recentSellPrice;

    public BasicCommodityItemVO(String id, String name, CommodityCategoryVO category, int repCount, double recentInPrice, double recentSellPrice) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.repCount = repCount;
        this.recentInPrice = recentInPrice;
        this.recentSellPrice = recentSellPrice;
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

    public CommodityCategoryVO getCategory() {
        return category;
    }

    public void setCategory(CommodityCategoryVO category) {
        this.category = category;
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

    @Override
    public boolean isCommodity() {
        return true;
    }
}
