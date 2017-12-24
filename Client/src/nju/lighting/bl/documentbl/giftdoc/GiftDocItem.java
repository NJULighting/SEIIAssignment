package nju.lighting.bl.documentbl.giftdoc;

import nju.lighting.bl.commoditybl.CommodityInfo;
import nju.lighting.bl.commoditybl.CommodityInfoImpl;
import nju.lighting.bl.documentbl.DocItem;
import nju.lighting.po.doc.giftdoc.GiftItemPO;
import nju.lighting.vo.doc.giftdoc.GiftItemVO;


/**
 * Created on 2017/11/14.
 * Description
 * @author 陈俊宇
 */
public class GiftDocItem implements DocItem {

    private int id;
    private int count;
    private double subtotal;
    private String commodityId;

    GiftDocItem(GiftItemVO vo) {
        id = vo.getId();
        count = vo.getCount();
        commodityId = vo.getCommodityID();

        subtotal = calculateSubtotal();
    }

    GiftDocItem(GiftItemPO po) {
        id = po.getId();
        count = po.getCount();
        commodityId = po.getCommodityID();

        subtotal = calculateSubtotal();
    }

    private double calculateSubtotal() {
        CommodityInfo commodityInfo = new CommodityInfoImpl();
        return commodityInfo.getCommodityRecentSellPrice(commodityId);
    }

    public int getCount() {
        return count;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public int getId() {
        return id;
    }

    public String getCommodityId() {
        return commodityId;
    }

    GiftItemPO toPO(String docId) {
        return new GiftItemPO(id, commodityId, count, subtotal, docId);
    }

    GiftItemVO toVO() {
        CommodityInfo commodityInfo = new CommodityInfoImpl();
        return new GiftItemVO(id, commodityInfo.getBasicCommodityItemVO(commodityId), count);
    }

    @Override
    public void redFlush() {
        count = -count;
        subtotal = -subtotal;
    }

    @Override
    public void approve() {

    }
}
