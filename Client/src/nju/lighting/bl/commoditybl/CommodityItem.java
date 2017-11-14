package nju.lighting.bl.commoditybl;

import nju.lighting.vo.commodity.CommodityItemVO;
import shared.CommodityTreeNode;

import java.util.Date;

/**
 * 商品全部信息领域模型对象
 */
public class CommodityItem implements CommodityTreeNode {

    private String modelNumber;

    private double inPrice;

    private double sellPrice;

    /**
     * 聚合 BasicCommodityItem
     */
    private BasicCommodityItem basicCommodityItem;

    private String batch;

    private String batchNumber;

    private Date dateOfProduction;

    public CommodityItem(String id, String name, CommodityCategoryItem category, String modelNumber,
                           int repCount, double inPrice, double sellPrice,
                           double recentInPrice, double recentSellPrice, String batch,
                           String batchNumber, Date dateOfProduction) {
        this.modelNumber = modelNumber;
        this.inPrice = inPrice;
        this.sellPrice = sellPrice;
        this.batch = batch;
        this.batchNumber = batchNumber;
        this.dateOfProduction = dateOfProduction;
        this.basicCommodityItem = new BasicCommodityItem(id, name, category, repCount, recentInPrice, recentSellPrice);
    }

    public String getModelNumber() {
        return modelNumber;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    public double getInPrice() {
        return inPrice;
    }

    public void setInPrice(double inPrice) {
        this.inPrice = inPrice;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public Date getDateOfProduction() {
        return dateOfProduction;
    }

    public void setDateOfProduction(Date dateOfProduction) {
        this.dateOfProduction = dateOfProduction;
    }

    public CommodityItemVO toVO() {
        return null;
    }

    public String getId() {
        return basicCommodityItem.getId();
    }

    public void setId(String id) {
        this.basicCommodityItem.setId(id);
    }

    public String getName() {
        return basicCommodityItem.getName();
    }

    public void setName(String name) {
        basicCommodityItem.setName(name);
    }

    public CommodityCategoryItem getCategory() {
        return basicCommodityItem.getCategory();
    }

    public void setCategory(CommodityCategoryItem category) {
        basicCommodityItem.setCategory(category);
    }

    public int getRepCount() {
        return basicCommodityItem.getRepCount();
    }

    public void setRepCount(int repCount) {
        basicCommodityItem.setRepCount(repCount);
    }

    public double getRecentInPrice() {
        return basicCommodityItem.getRecentInPrice();
    }

    public void setRecentInPrice(double recentInPrice) {
        basicCommodityItem.setRecentInPrice(recentInPrice);
    }

    public double getRecentSellPrice() {
        return basicCommodityItem.getRecentSellPrice();
    }

    public void setRecentSellPrice(double recentSellPrice) {
        basicCommodityItem.setRecentSellPrice(recentSellPrice);
    }

    public BasicCommodityItem toBasicCommodityItem() {
        return basicCommodityItem;
    }

    @Override
    public boolean isCommodity() {
        return true;
    }
}
