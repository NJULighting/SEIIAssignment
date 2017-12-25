package nju.lighting.bl.promotionbl;

import nju.lighting.bl.commoditybl.CommodityInfo;
import nju.lighting.bl.commoditybl.CommodityInfoImpl;
import nju.lighting.po.promotion.PromotionPackageItemPO;
import nju.lighting.vo.doc.giftdoc.GiftItemVO;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created on 2017/12/7.
 * Description:
 * @author Liao
 */
class PromotionItemList {
    private List<GiftItemVO> goods;

    PromotionItemList() {
    }

    PromotionItemList(List<GiftItemVO> goods) {
        this.goods = goods;
    }

    List<PromotionPackageItemPO> toPOList(int promotionID) {
        return goods.stream()
                .map(giftItemVO -> new PromotionPackageItemPO(giftItemVO.getCommodityID(), promotionID, giftItemVO.getCount()))
                .collect(Collectors.toList());
    }

    void addAll(List<PromotionPackageItemPO> goods) {
        // TODO: 2017/12/8 commodity info
        CommodityInfo commodityInfo = new CommodityInfoImpl();
    }

    List<GiftItemVO> getGoods() {
        return goods;
    }
}
