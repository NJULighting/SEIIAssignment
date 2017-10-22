package nju.lighting.vo;


/**
 * Created on 2017/10/22.
 * Description
 *
 * @author 陈俊宇
 */
public class GiftItemVO {

    private CommodityVO commodity;

    private int count;


    public GiftItemVO(CommodityVO commodity, int count) {
        this.commodity = commodity;
        this.count = count;
    }

    public CommodityVO getCommodity() {
        return commodity;
    }

    public void setCommodity(CommodityVO commodity) {
        this.commodity = commodity;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
