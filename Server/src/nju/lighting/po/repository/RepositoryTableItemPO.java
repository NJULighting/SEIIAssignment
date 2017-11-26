package nju.lighting.po.repository;

import nju.lighting.po.commodity.CommodityCategoryPO;
import nju.lighting.po.commodity.CommodityItemPO;

import java.util.Date;

public class RepositoryTableItemPO {

    private String name;

    private String commodityId;

    private String modelNumber;

    private int repCount;

    private double recentInPrice;

    private String batch;

    private String batchNumber;

    private Date dateOfProduction;

    /**
     * 用于测试时打印
     * @return
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(name);
        builder.append(" ");
        builder.append(modelNumber);
        builder.append(" ");
        builder.append(repCount);
        builder.append(" ");
        builder.append(recentInPrice);
        builder.append(" ");
        builder.append(batch);
        builder.append(" ");
        builder.append(batchNumber);
        builder.append(" ");
        builder.append(dateOfProduction);
        return builder.toString();
    }

    public RepositoryTableItemPO(String name, String commodityId, String modelNumber, int repCount, double recentInPrice, String batch, String batchNumber, Date dateOfProduction) {
        this.name = name;
        this.commodityId = commodityId;
        this.modelNumber = modelNumber;
        this.repCount = repCount;
        this.recentInPrice = recentInPrice;
        this.batch = batch;
        this.batchNumber = batchNumber;
        this.dateOfProduction = dateOfProduction;
    }

    public RepositoryTableItemPO(CommodityItemPO commodityItemPO) {
        this.name = commodityItemPO.getName();
        this.commodityId = commodityItemPO.getId();
        this.modelNumber = commodityItemPO.getModelNumber();
        this.repCount = commodityItemPO.getRepCount();
        this.recentInPrice = commodityItemPO.getRecentInPrice();
        this.batch = commodityItemPO.getBatch();
        this.batch = commodityItemPO.getBatchNumber();
        this.dateOfProduction = commodityItemPO.getDateOfProduction();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
    }

    public String getModelNumber() {
        return modelNumber;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
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
}
