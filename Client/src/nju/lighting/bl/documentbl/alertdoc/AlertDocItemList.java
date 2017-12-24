package nju.lighting.bl.documentbl.alertdoc;

import nju.lighting.bl.commoditybl.CommodityInfo;
import nju.lighting.bl.commoditybl.CommodityInfoImpl;
import nju.lighting.po.doc.alertdoc.AlertDocItemPO;
import nju.lighting.vo.doc.alertdoc.AlertDocItemVO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created on 2017/12/18.
 * Description:
 * @author Liao
 */
class AlertDocItemList {
    private List<AlertDocItem> items;
    private CommodityInfo commodityInfo;

    AlertDocItemList() {
        items = new ArrayList<>();
        commodityInfo = new CommodityInfoImpl();
    }

    void add(AlertDocItemPO itemPO) {
        items.add(new AlertDocItem(itemPO));
    }

    void add(AlertDocItemVO itemVO) {
        items.add(new AlertDocItem(itemVO));
    }

    boolean containsCommodity(String commodityName) {
        for (AlertDocItem item : items) {
            String itemCommodityName = commodityInfo.getCommodityNameByID(item.getCommodityId());
            if (itemCommodityName.equals(commodityName)) {
                return true;
            }
        }
        return false;
    }

    List<AlertDocItemPO> toPO(String docId) {
        return items.stream()
                .map(alertDocItem -> alertDocItem.toPO(docId))
                .collect(Collectors.toList());
    }

    List<AlertDocItemVO> toVO() {
        return items.stream()
                .map(AlertDocItem::toVO)
                .collect(Collectors.toList());
    }
}
