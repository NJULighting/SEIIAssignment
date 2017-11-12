package nju.lighting.vo.doc.giftdoc;


import nju.lighting.vo.commodity.BasicCommodityItemVO;

/**
 * Created on 2017/10/22.
 * Description
 * @author 陈俊宇
 */
public class GiftItemVO {

    private BasicCommodityItemVO commodity;

    private int count;


    public GiftItemVO(BasicCommodityItemVO commodity, int count) {
        this.commodity = commodity;
        this.count = count;
    }

    public BasicCommodityItemVO getCommodity() {
        return commodity;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
