package nju.lighting.bl.documentbl.stockdoc;

import nju.lighting.bl.commoditybl.CommodityInfo;
import nju.lighting.bl.commoditybl.CommodityInfoImpl;
import nju.lighting.bl.documentbl.ItemList;
import nju.lighting.po.doc.stockdoc.StockDocItemPO;
import nju.lighting.vo.doc.stockdoc.StockDocItemVO;
import shared.ResultMessage;

import java.util.List;

/**
 * Created on 2017/12/21.
 * Description:
 * @author Liao
 */
class StockDocItemList {

    private ItemList<StockDocItem> itemList = new ItemList<>();
    private StockDocItemType itemType;

    StockDocItemList(StockDocItemType itemType) {
        this.itemType = itemType;
    }

    boolean containsCommodity(String commodityName) {
        CommodityInfo commodityInfo = new CommodityInfoImpl();
        return itemList.containItemWithAttribute(commodityName,
                item -> commodityInfo.getCommodityNameByID(item.getCommodityID()));
    }

    void add(StockDocItemPO po) {
        itemList.add(po, item -> new StockDocItem(item, itemType));
    }

    void add(StockDocItemVO vo) {
        itemList.add(vo, item -> new StockDocItem(item, itemType));
    }

    List<StockDocItemPO> toPO(String docId) {
        return itemList.toPO(item -> item.toPO(docId));
    }

    List<StockDocItemVO> toVO() {
        return itemList.toVO(StockDocItem::toVO);
    }

    double getRevenue() {
        return itemList.transformItemToNumber(StockDocItem::getRevenue);
    }

    public void redFlush() {
        itemList.redFlush();
    }

    ResultMessage approve() {
        return itemList.approve();
    }
}
