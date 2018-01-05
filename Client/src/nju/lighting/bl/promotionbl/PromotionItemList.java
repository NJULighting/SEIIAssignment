package nju.lighting.bl.promotionbl;

import nju.lighting.bl.utils.CollectionTransformer;
import nju.lighting.po.promotion.PromotionPackageItemPO;
import nju.lighting.vo.doc.giftdoc.GiftItemVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2017/12/7.
 * Description:
 * @author Liao
 */
class PromotionItemList {
    private List<PromotionItem> itemList = new ArrayList<>();

    List<PromotionPackageItemPO> toPOList(int promotionID) {
        return CollectionTransformer.toList(itemList, item -> item.toPO(promotionID));
    }

    List<GiftItemVO> toVOList() {
        return CollectionTransformer.toList(itemList, PromotionItem::toVO);
    }

    void addAllVO(List<GiftItemVO> goods) {
        goods.forEach(this::add);
    }

    void addAllPO(List<PromotionPackageItemPO> goods) {
        goods.forEach(this::add);
    }

    private void add(GiftItemVO itemVO) {
        itemList.add(new PromotionItem(itemVO));
    }

    private void add(PromotionPackageItemPO itemPO) {
        itemList.add(new PromotionItem(itemPO));
    }
}
