package nju.lighting.bl.commoditybl;

import nju.lighting.po.commodity.CommodityItemPO;
import nju.lighting.vo.commodity.CommodityItemVO;

import java.util.Date;

/**
 * 商品全部信息领域模型对象
 */
class CommodityItem {

    private String modelNumber;
    private double inPrice;
    private double sellPrice;
    private BasicCommodityItem basicCommodityItem;
    private String batch;
    private String batchNumber;
    private Date dateOfProduction;

    CommodityItem(String id, String name, int category, String modelNumber,
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

    CommodityItem(CommodityItemPO po) {
        this(po.getId(), po.getName(), po.getCategoryId(), po.getModelNumber(), po.getRepCount(), po.getInPrice(),
                po.getSellPrice(), po.getRecentInPrice(), po.getRecentSellPrice(), po.getBatch(), po.getBatchNumber(),
                po.getDateOfProduction());
    }

    public String getModelNumber() {
        return modelNumber;
    }

    public double getInPrice() {
        return inPrice;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public BasicCommodityItem getBasicCommodityItem() {
        return basicCommodityItem;
    }

    public String getBatch() {
        return batch;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public Date getDateOfProduction() {
        return dateOfProduction;
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

    public int getCategory() {
        return basicCommodityItem.getCategoryID();
    }

    BasicCommodityItem toBasicCommodityItem() {
        return basicCommodityItem;
    }
}
