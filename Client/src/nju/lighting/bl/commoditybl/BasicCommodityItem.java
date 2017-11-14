package nju.lighting.bl.commoditybl;

import nju.lighting.vo.commodity.BasicCommodityItemVO;
import shared.CommodityTreeNode;

/**
 * 商品基本信息领域对象模型
 */
public class BasicCommodityItem implements CommodityTreeNode {

    private String id;

    private String name;

    private CommodityCategoryItem category;

    private int repCount;

    private double recentInPrice;

    private double recentSellPrice;

    public BasicCommodityItem(String id, String name, CommodityCategoryItem category, int repCount, double recentInPrice, double recentSellPrice) {
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

    public CommodityCategoryItem getCategory() {
        return category;
    }

    public void setCategory(CommodityCategoryItem category) {
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

    public BasicCommodityItemVO toVo() {
        return null;
    }


    @Override
    public boolean isCommodity() {
        return true;
    }
}
