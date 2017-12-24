package nju.lighting.bl.documentbl.stockdoc;

import nju.lighting.bl.commoditybl.CommodityInfo;
import nju.lighting.bl.commoditybl.CommodityInfoImpl;
import nju.lighting.bl.documentbl.DocItem;
import nju.lighting.po.doc.stockdoc.StockDocItemPO;
import nju.lighting.vo.doc.stockdoc.StockDocItemVO;

/**
 * Description:
 * 进货类单据里的商品条目
 */
public class StockDocItem implements DocItem {

    private int id;
    private String commodityID;
    private int number;
    private double salePrice;
    private double totalAmount;
    private String remarks = "";

    StockDocItem(StockDocItemPO po) {
        id = po.getId();
        commodityID = po.getCommodityID();
        number = po.getNumber();
        remarks = po.getRemarks();

        updatePriceAndTotal();
    }

    StockDocItem(StockDocItemVO vo) {
        id = vo.getId();
        commodityID = vo.getCommodity().getId();
        number = vo.getNumber();
        remarks = vo.getRemarks();

        updatePriceAndTotal();
    }

    private void updatePriceAndTotal() {
        CommodityInfo commodityInfo = new CommodityInfoImpl();
        salePrice = commodityInfo.getCommodityRecentSellPrice(commodityID);
        totalAmount = number * salePrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommodityID() {
        return commodityID;
    }

    public void setCommodityID(String commodityID) {
        this.commodityID = commodityID;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
        updatePriceAndTotal();
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
        updatePriceAndTotal();
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    StockDocItemPO toPO(String docId) {
        return new StockDocItemPO(id, docId, commodityID, number, totalAmount, remarks);
    }

    StockDocItemVO toVO() {
        CommodityInfo commodityInfo = new CommodityInfoImpl();
        return new StockDocItemVO(id, commodityInfo.getBasicCommodityItemVO(commodityID),
                number, totalAmount, remarks);
    }

    @Override
    public void redFlush() {
        number = -number;
        totalAmount = -totalAmount;
    }

    @Override
    public void approve() {

    }
}
