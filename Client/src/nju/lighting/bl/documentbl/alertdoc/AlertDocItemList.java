package nju.lighting.bl.documentbl.alertdoc;

import nju.lighting.bl.commoditybl.CommodityInfo;
import nju.lighting.bl.commoditybl.CommodityInfoImpl;
import nju.lighting.bl.documentbl.ItemList;
import nju.lighting.po.doc.alertdoc.AlertDocItemPO;
import nju.lighting.vo.doc.alertdoc.AlertDocItemVO;

import java.util.List;

/**
 * Created on 2017/12/18.
 * Description:
 * @author Liao
 */
class AlertDocItemList {
    private ItemList<AlertDocItem> itemList = new ItemList<>();
    private CommodityInfo commodityInfo;

    AlertDocItemList() {
        commodityInfo = new CommodityInfoImpl();
    }

    void add(AlertDocItemPO itemPO) {
        itemList.add(itemPO, AlertDocItem::new);
    }

    void add(AlertDocItemVO itemVO) {
        itemList.add(itemVO, AlertDocItem::new);
    }

    boolean containsCommodity(String commodityName) {
        return itemList.containItemWithAttribute(commodityName,
                item -> commodityInfo.getCommodityNameByID(item.getCommodityId()));
    }

    List<AlertDocItemPO> toPO(String docId) {
        return itemList.toPO(item -> item.toPO(docId));
    }

    List<AlertDocItemVO> toVO() {
        return itemList.toVO(AlertDocItem::toVO);
    }

    boolean triggered(String commodityId, int count) {
        List<AlertDocItem> docItemList = itemList.getItems();
        for (AlertDocItem docItem : docItemList) {
            if (docItem.triggered(commodityId, count))
                return true;
        }
        return false;
    }
}
