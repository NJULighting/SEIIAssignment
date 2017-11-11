package nju.lighting.vo.repository;

import java.util.Date;

public class RepositoryTableItemVO {

    private String commodityId;

    private String modelNumber;

    private int repCount;

    private double recentInPrice;

    private String batch;

    private String batchNumber;

    private Date dateOfProduction;

    public RepositoryTableItemVO(String commodityId, String modelNumber,
                                 int repCount, double recentInPrice,
                                 String batch, String batchNumber, Date dateOfProduction) {
        this.commodityId = commodityId;
        this.modelNumber = modelNumber;
        this.repCount = repCount;
        this.recentInPrice = recentInPrice;
        this.batch = batch;
        this.batchNumber = batchNumber;
        this.dateOfProduction = dateOfProduction;
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