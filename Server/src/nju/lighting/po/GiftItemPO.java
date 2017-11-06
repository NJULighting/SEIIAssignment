package nju.lighting.po;

import nju.lighting.po.commodity.CommodityItemPO;

/**
 * Created on 2017/10/22.
 * Description
 * @author 陈俊宇
 */
public class GiftItemPO {
    private CommodityItemPO commodity;

    private int count;

    public GiftItemPO(CommodityItemPO commodity, int count) {
        this.commodity = commodity;
        this.count = count;
    }

    public CommodityItemPO getCommodity() {
        return commodity;
    }

    public void setCommodityName(CommodityItemPO commodity) {
        this.commodity = commodity;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
