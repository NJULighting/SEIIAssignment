package nju.lighting.po.commodity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "COMMODITY")
public class CommodityItemPO {

    private String id;

    private String name;

    private int categoryId;

    private String modelNumber;

    private int repCount;

    private double inPrice;

    private double sellPrice;

    private double recentInPrice;

    private double recentSellPrice;

    private String batch;

    private String batchNumber;

    private Date dateOfProduction;

    public CommodityItemPO() {

    }

    public CommodityItemPO(String id, String name, int categoryId, String modelNumber,
                           int repCount, double inPrice, double sellPrice,
                           double recentInPrice, double recentSellPrice, String batch,
                           String batchNumber, Date dateOfProduction) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
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

    @Id
    @Column(name = "ID", length = 36, nullable = false)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "NAME", length = 50, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "CATEGORY_ID")
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Column(name = "MODEL_NUMBER", length = 36, nullable = false)
    public String getModelNumber() {
        return modelNumber;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    @Column(name = "REP_COUNT", nullable = false)
    public int getRepCount() {
        return repCount;
    }

    public void setRepCount(int repCount) {
        this.repCount = repCount;
    }

    @Column(name = "IN_PRICE", nullable = false)
    public double getInPrice() {
        return inPrice;
    }

    public void setInPrice(double inPrice) {
        this.inPrice = inPrice;
    }

    @Column(name = "SELL_PRICE", nullable = false)
    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    @Column(name = "RECENT_IN_PRICE", nullable = false)
    public double getRecentInPrice() {
        return recentInPrice;
    }

    public void setRecentInPrice(double recentInPrice) {
        this.recentInPrice = recentInPrice;
    }

    @Column(name = "RECENT_SELL_PRICE", nullable = false)
    public double getRecentSellPrice() {
        return recentSellPrice;
    }

    public void setRecentSellPrice(double recentSellPrice) {
        this.recentSellPrice = recentSellPrice;
    }

    @Column(name = "BATCH", length = 40, nullable = false)
    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    @Column(name = "BATCH_NUMBER", length = 40, nullable = false)
    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    @Column(name = "DATA_OF_PRODUCTION", length = 40, nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getDateOfProduction() {
        return dateOfProduction;
    }

    public void setDateOfProduction(Date dateOfProduction) {
        this.dateOfProduction = dateOfProduction;
    }
}
