package nju.lighting.bl.documentbl.giftdoc;

import nju.lighting.bl.commoditybl.CommodityInfo;
import nju.lighting.bl.commoditybl.CommodityInfoImpl;
import nju.lighting.bl.documentbl.ItemList;
import nju.lighting.po.doc.giftdoc.GiftItemPO;
import nju.lighting.vo.doc.giftdoc.GiftItemVO;

import java.util.List;

/**
 * Created on 2017/12/21.
 * Description:
 * @author Liao
 */
class GiftDocItemList {


    private ItemList<GiftDocItem> itemList = new ItemList<>();

    void add(GiftItemPO po) {
        itemList.add(po, GiftDocItem::new);
    }

    void add(GiftItemVO vo) {
        itemList.add(vo, GiftDocItem::new);
    }

    List<GiftItemPO> toPO(String docId) {
        return itemList.toPO(item -> item.toPO(docId));
    }

    List<GiftItemVO> toVO() {
        return itemList.toVO(GiftDocItem::toVO);
    }

    void redFlush() {
        itemList.redFlush();
    }

    boolean containsCommodity(String name) {
        CommodityInfo commodityInfo = new CommodityInfoImpl();
        return itemList.containItemWithAttribute(name,
                item -> commodityInfo.getCommodityNameByID(item.getCommodityId()));
    }
}
