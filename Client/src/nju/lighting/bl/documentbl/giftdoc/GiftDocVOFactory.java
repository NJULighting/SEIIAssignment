package nju.lighting.bl.documentbl.giftdoc;

import nju.lighting.bl.commoditybl.CommodityInfo;
import nju.lighting.bl.commoditybl.CommodityInfoImpl;
import nju.lighting.bl.documentbl.DocVOFactory;
import nju.lighting.bl.utils.VPOTransformer;
import nju.lighting.po.doc.DocPO;
import nju.lighting.po.doc.giftdoc.GiftDocPO;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.doc.giftdoc.GiftDocVO;
import nju.lighting.vo.doc.giftdoc.GiftItemVO;

import java.util.List;

/**
 * Created on 2017/12/17.
 * Description:
 * @author Liao
 */
public class GiftDocVOFactory implements DocVOFactory {

    @Override
    public DocVO poToDocVO(DocPO po) {
        GiftDocPO giftDocPO = (GiftDocPO) po;
        CommodityInfo commodityInfo = new CommodityInfoImpl();

        // Item list
        List<GiftItemVO> itemVOList = VPOTransformer.toVPOList(giftDocPO.getGiftItemPOs(),
                giftItemPO -> new GiftItemVO(giftItemPO.getId(), commodityInfo.getBasicCommodityItemVO(giftItemPO.getCommodityID()),
                        giftItemPO.getCount()));

        return new GiftDocVO(po.getCreateTime(), po.getUserId(), po.getId(), itemVOList,
                giftDocPO.getCustomerID(), giftDocPO.getPromotionId());
    }
}
