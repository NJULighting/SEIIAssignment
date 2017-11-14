package nju.lighting.bl.documentbl.giftdoc;

import nju.lighting.bl.commoditybl.BasicCommodityItem;



/**
 * Created on 2017/11/14.
 * Description
 *
 * @author 陈俊宇
 */
public class GiftDocItem {

    BasicCommodityItem gifts;

    int count;

    double subtotal;

    public GiftDocItem(BasicCommodityItem gifts, int count) {
        this.gifts = gifts;
        this.count = count;
    }

    public BasicCommodityItem getGifts() {
        return gifts;
    }

    public void setGifts(BasicCommodityItem gifts) {
        this.gifts = gifts;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
}
