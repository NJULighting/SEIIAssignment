package nju.lighting.po.commodity;

import java.util.Date;

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
}
