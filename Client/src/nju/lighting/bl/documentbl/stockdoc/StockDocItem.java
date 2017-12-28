package nju.lighting.bl.documentbl.stockdoc;

import nju.lighting.bl.commoditybl.CommodityInfo;
import nju.lighting.bl.commoditybl.CommodityInfoImpl;
import nju.lighting.bl.documentbl.DocItem;
import nju.lighting.bl.documentbl.RedFlush;
import nju.lighting.bl.repositorybl.RepositoryInfo;
import nju.lighting.bl.repositorybl.RepositoryInfoImpl;
import nju.lighting.po.doc.stockdoc.StockDocItemPO;
import nju.lighting.vo.doc.stockdoc.StockDocItemVO;
import shared.RepositoryChangeType;
import shared.ResultMessage;

/**
 * Description:
 * 进货类单据里的商品条目
 */
public class StockDocItem implements DocItem {

    private int id;
    private String commodityID;
    private int number;
    private double totalAmount;
    private String remarks = "";
    private StockDocItemType itemType;

    StockDocItem(StockDocItemPO po, StockDocItemType itemType) {
        id = po.getId();
        commodityID = po.getCommodityID();
        number = po.getNumber();
        remarks = po.getRemarks();
        totalAmount = po.getTotalAmount();
        this.itemType = itemType;
    }

    StockDocItem(StockDocItemVO vo, StockDocItemType itemType) {
        id = vo.getId();
        commodityID = vo.getCommodity().getId();
        number = vo.getNumber();
        remarks = vo.getRemarks();
        totalAmount = vo.getTotalAmount();
        this.itemType = itemType;
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

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    double getRevenue() {
        CommodityInfo commodityInfo = new CommodityInfoImpl();
        return commodityInfo.getCommodityInPrice(commodityID) * number - totalAmount;
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
        id = 0;
        number = -number;
        totalAmount = -totalAmount;
        remarks = RedFlush.RED_FLUSH_COMMENT;
    }

    @Override
    public ResultMessage approve() {
        // Change repository
        RepositoryInfo repositoryInfo = new RepositoryInfoImpl();
        RepositoryChangeType changeType = itemType == StockDocItemType.RETURN ?
                RepositoryChangeType.RETURN : RepositoryChangeType.BUY;

        return repositoryInfo.changeRepository(commodityID, number, totalAmount, changeType);
    }
}
