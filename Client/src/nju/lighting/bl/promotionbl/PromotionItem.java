package nju.lighting.bl.promotionbl;

import nju.lighting.bl.commoditybl.CommodityInfo;
import nju.lighting.bl.commoditybl.CommodityInfoImpl;
import nju.lighting.po.promotion.PromotionPackageItemPO;
import nju.lighting.vo.doc.giftdoc.GiftItemVO;

/**
 * Created on 2017/12/26.
 * Description:
 * @author Liao
 */
class PromotionItem {
    private String commodityId;
    private int id;
    private int commodityCount;

    PromotionItem(PromotionPackageItemPO po) {
        id = po.getId();
        commodityCount = po.getCommodityCount();
        commodityId = po.getCommodityId();
    }

    PromotionItem(GiftItemVO vo) {
        id = vo.getId();
        commodityCount = vo.getCount();
        commodityId = vo.getCommodityID();
    }

    GiftItemVO toVO() {
        CommodityInfo commodityInfo = new CommodityInfoImpl();
        return new GiftItemVO(commodityInfo.getBasicCommodityItemVO(commodityId), commodityCount);
    }

    PromotionPackageItemPO toPO(int promotionId) {
        return new PromotionPackageItemPO(id, commodityCount, commodityId, promotionId);
    }
}
