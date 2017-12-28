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
        this.basicCommodityItem = new BasicCommodityItem(id, name, category, repCount, recentInPrice, recentSellPrice, modelNumber);
    }

    CommodityItem(CommodityItemPO po) {
        this(po.getId(), po.getName(), po.getCategoryId(), po.getModelNumber(), po.getRepCount(), po.getInPrice(),
                po.getSellPrice(), po.getRecentInPrice(), po.getRecentSellPrice(), po.getBatch(), po.getBatchNumber(),
                po.getDateOfProduction());
    }

    CommodityItem(CommodityItemVO vo, int categoryID) {
        basicCommodityItem = new BasicCommodityItem(vo.getId(), vo.getName(), categoryID, vo.getRepCount(),
                vo.getRecentInPrice(), vo.getRecentSellPrice(), vo.getModelNumber());
        modelNumber = vo.getModelNumber();
        inPrice = vo.getInPrice();
        sellPrice = vo.getSellPrice();
        batch = vo.getBatch();
        batchNumber = vo.getBatchNumber();
        dateOfProduction = vo.getDateOfProduction();
    }

    CommodityItemVO toVO() {
        return new CommodityItemVO(getId(), getName(), modelNumber, getRepCount(), inPrice, sellPrice,
                basicCommodityItem.getRecentInPrice(), basicCommodityItem.getRecentSellPrice(), batch,
                batchNumber, dateOfProduction);
    }

    private int getRepCount() {
        return basicCommodityItem.getRepCount();
    }

    private double getRecentInPrice() {
        return basicCommodityItem.getRecentInPrice();
    }

    private double getRecentSellPrice() {
        return basicCommodityItem.getRecentSellPrice();
    }

    String getId() {
        return basicCommodityItem.getId();
    }

    String getName() {
        return basicCommodityItem.getName();
    }

    int getCategory() {
        return basicCommodityItem.getCategoryID();
    }

    BasicCommodityItem toBasicCommodityItem() {
        return basicCommodityItem;
    }

    public CommodityItemPO toPO() {
        return new CommodityItemPO(getId(), getName(), getCategory(), modelNumber,
                getRepCount(), inPrice, sellPrice, getRecentInPrice(),
                getRecentSellPrice(), batch, batchNumber, dateOfProduction);
    }


}
