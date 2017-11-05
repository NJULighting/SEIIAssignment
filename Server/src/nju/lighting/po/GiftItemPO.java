package nju.lighting.po;

/**
 * Created on 2017/10/22.
 * Description
 * @author 陈俊宇
 */
public class GiftItemPO {
    private CommodityPO commodity;

    private int count;

    public GiftItemPO(CommodityPO commodity, int count) {
        this.commodity = commodity;
        this.count = count;
    }

    public CommodityPO getCommodity() {
        return commodity;
    }

    public void setCommodityName(CommodityPO commodity) {
        this.commodity = commodity;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
