package nju.lighting.vo.commodity;

import java.util.Date;

/**
 * 商品的详细信息，提供给商品模块、库存模块使用
 */
public class CommodityItemVO implements Nameable {

    private String id;
    private String name;
    private String modelNumber;
    private int repCount;
    private double inPrice;
    private double sellPrice;
    private double recentInPrice;
    private double recentSellPrice;
    private String batch;
    private String batchNumber;
    private Date dateOfProduction;

    public CommodityItemVO(String id, String name,
                           String modelNumber, int repCount, double inPrice, double sellPrice,
                           double recentInPrice, double recentSellPrice, String batch,
                           String batchNumber, Date dateOfProduction) {
        this.id = id;
        this.name = name;
        this.modelNumber = modelNumber;
        this.repCount = repCount;
        this.inPrice = inPrice;
        this.sellPrice = sellPrice;
        this.recentInPrice = recentInPrice;
        this.recentSellPrice = recentSellPrice;
        this.batch = batch;
        this.batchNumber = batchNumber;
        this.dateOfProduction = dateOfProduction;
    }

    public CommodityItemVO(String name, String modelNumber, int repCount, double inPrice,
                           double sellPrice, double recentInPrice, double recentSellPrice, String batch,
                           String batchNumber, Date dateOfProduction) {
        this.name = name;
        this.modelNumber = modelNumber;
        this.repCount = repCount;
        this.inPrice = inPrice;
        this.sellPrice = sellPrice;
        this.recentInPrice = recentInPrice;
        this.recentSellPrice = recentSellPrice;
        this.batch = batch;
        this.batchNumber = batchNumber;
        this.dateOfProduction = dateOfProduction;
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

    public BasicCommodityItemVO toBasicCommodityItem() {
        return new BasicCommodityItemVO(id, name, repCount, recentInPrice, recentSellPrice,modelNumber);
    }


}
