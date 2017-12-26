package nju.lighting.bl.documentbl.salesdoc;

import nju.lighting.bl.commoditybl.CommodityInfo;
import nju.lighting.bl.commoditybl.CommodityInfoImpl;
import nju.lighting.bl.documentbl.DocItem;
import nju.lighting.bl.documentbl.RedFlush;
import nju.lighting.bl.repositorybl.RepositoryInfo;
import nju.lighting.bl.repositorybl.RepositoryInfoImpl;
import nju.lighting.po.doc.salesdoc.SalesDocItemPO;
import nju.lighting.vo.doc.salesdoc.SalesDocItemVO;
import shared.RepositoryChangeType;
import shared.ResultMessage;

/**
 * Description:
 * 销售类单据里的商品条目
 */
public class SalesDocItem implements DocItem {

    private int id;
    private String commodityID;
    private int number;
    private double salePrice;
    private double totalAmount;
    private String remarks = "";
    private SalesDocItemType itemType;

    SalesDocItem(SalesDocItemPO po, SalesDocItemType itemType) {
        id = po.getId();
        commodityID = po.getCommodityID();
        number = po.getNumber();
        remarks = po.getRemarks();
        this.itemType = itemType;

        updatePriceAndTotal();
    }

    SalesDocItem(SalesDocItemVO vo, SalesDocItemType itemType) {
        id = vo.getId();
        commodityID = vo.getCommodity().getId();
        number = vo.getNumber();
        remarks = vo.getRemarks();
        this.itemType = itemType;

        updatePriceAndTotal();
    }

    private void updatePriceAndTotal() {
        CommodityInfo commodityInfo = new CommodityInfoImpl();
        salePrice = commodityInfo.getCommodityRecentSellPrice(commodityID);
        totalAmount = salePrice * number;
    }

    public int getId() {
        return id;
    }

    public String getCommodityID() {
        return commodityID;
    }

    public int getNumber() {
        return number;
    }

    double getSalePrice() {
        return salePrice;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getRemarks() {
        return remarks;
    }

    SalesDocItemPO toPO(String docId) {
        return new SalesDocItemPO(id, docId, commodityID, number, totalAmount, remarks);
    }

    SalesDocItemVO toVO() {
        CommodityInfo commodityInfo = new CommodityInfoImpl();
        return new SalesDocItemVO(id, commodityInfo.getBasicCommodityItemVO(commodityID), number,
                totalAmount, remarks);
    }

    @Override
    public void redFlush() {
        id = 0;
        number = -number;
        totalAmount = -totalAmount;
        remarks = RedFlush.RED_FLUSH_COMMENT;
    }

    @Override
    public ResultMessage approve() {
        RepositoryInfo repositoryInfo = new RepositoryInfoImpl();
        RepositoryChangeType repositoryChangeType = itemType == SalesDocItemType.RETURN ?
                RepositoryChangeType.BE_RETURN : RepositoryChangeType.SELL;

        return repositoryInfo.changeRepository(commodityID, number, totalAmount, repositoryChangeType);
    }
}
