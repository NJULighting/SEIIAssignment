package nju.lighting.bl.documentbl.lossandgaindoc;

import nju.lighting.bl.commoditybl.CommodityInfo;
import nju.lighting.bl.commoditybl.CommodityInfoImpl;
import nju.lighting.bl.documentbl.ItemList;
import nju.lighting.po.doc.lossandgaindoc.LossAndGainItemPO;
import nju.lighting.vo.doc.lossandgaindoc.LossAndGainDocItemVO;
import shared.LossAndGainItemType;

import java.util.List;
import java.util.function.ToDoubleFunction;

/**
 * Created on 2017/12/21.
 * Description:
 * @author Liao
 */
class LossAndGainDocItemList {

    private LossAndGainItemType lossAndGainItemType;
    private ItemList<LossAndGainDocItem> itemList = new ItemList<>();

    boolean containCommodity(String commodityName) {
        return itemList.containItemWithAttribute(commodityName, LossAndGainDocItem::getCommodityName);
    }

    void add(LossAndGainItemPO po) {
        itemList.add(po, LossAndGainDocItem::new);
        lossAndGainItemType = po.getType();
    }

    void add(LossAndGainDocItemVO vo) {
        itemList.add(vo, LossAndGainDocItem::new);
    }

    List<LossAndGainItemPO> toPO(String docId) {
        return itemList.toPO(docId, item -> item.toPO(docId));
    }

    List<LossAndGainDocItemVO> toVO() {
        return itemList.toVO(LossAndGainDocItem::toVO);
    }

    double getAmount() {
        CommodityInfo commodityInfo = new CommodityInfoImpl();
        ToDoubleFunction<LossAndGainDocItem> function =
                item -> commodityInfo.getCommodityRecentSellPrice(item.getCommodityId()) * item.getCount();
        if (lossAndGainItemType == LossAndGainItemType.GAIN)
            return itemList.transformItemToNumber(function);
        else
            return -itemList.transformItemToNumber(function);
    }
}
