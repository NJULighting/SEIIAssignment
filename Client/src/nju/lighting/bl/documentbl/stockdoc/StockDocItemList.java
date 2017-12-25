package nju.lighting.bl.documentbl.stockdoc;

import nju.lighting.bl.commoditybl.CommodityInfo;
import nju.lighting.bl.commoditybl.CommodityInfoImpl;
import nju.lighting.bl.documentbl.ItemList;
import nju.lighting.po.doc.stockdoc.StockDocItemPO;
import nju.lighting.vo.doc.stockdoc.StockDocItemVO;

import java.util.List;

/**
 * Created on 2017/12/21.
 * Description:
 * @author Liao
 */
class StockDocItemList {

    private ItemList<StockDocItem> itemList = new ItemList<>();

    boolean containsCommodity(String commodityName) {
        CommodityInfo commodityInfo = new CommodityInfoImpl();
        return itemList.containItemWithAttribute(commodityName,
                item -> commodityInfo.getCommodityNameByID(item.getCommodityID()));
    }

    void add(StockDocItemPO po) {
        itemList.add(po, StockDocItem::new);
    }

    void add(StockDocItemVO vo) {
        itemList.add(vo, StockDocItem::new);
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
}
