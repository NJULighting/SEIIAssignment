package nju.lighting.bl.documentbl.lossandgaindoc;

import nju.lighting.bl.commoditybl.CommodityInfo;
import nju.lighting.bl.commoditybl.CommodityInfoImpl;
import nju.lighting.bl.documentbl.ItemList;
import nju.lighting.po.doc.lossandgaindoc.LossAndGainItemPO;
import nju.lighting.vo.doc.lossandgaindoc.LossAndGainDocItemVO;

import java.util.List;

/**
 * Created on 2017/12/21.
 * Description:
 * @author Liao
 */
class LossAndGainDocItemList {

    boolean containCommodity(String commodityName) {
        return itemList.containItemWithAttribute(commodityName, LossAndGainDocItem::getCommodityName);
    }

    private ItemList<LossAndGainDocItem> itemList = new ItemList<>();

    void add(LossAndGainItemPO po) {
        itemList.add(po, LossAndGainDocItem::new);
    }

    void add(LossAndGainDocItemVO vo) {
        itemList.add(vo, LossAndGainDocItem::new);
    }

    List<LossAndGainItemPO> toPO(String docId) {
        return itemList.toPO(docId, item -> item.toPO(docId));
    }
}
