package nju.lighting.vo.doc.lossandgaindoc;

import nju.lighting.vo.commodity.BasicCommodityItemVO;
import shared.LossAndGainItemType;

public class LossAndGainDocItemVO {

    private BasicCommodityItemVO commodity;

    private int count;

    private LossAndGainItemType type;

    public BasicCommodityItemVO getCommodity() {
        return commodity;
    }

    public void setCommodity(BasicCommodityItemVO commodity) {
        this.commodity = commodity;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public LossAndGainItemType getType() {
        return type;
    }

    public void setType(LossAndGainItemType type) {
        this.type = type;
    }

    public LossAndGainDocItemVO(BasicCommodityItemVO commodity, int count, LossAndGainItemType type) {
        this.commodity = commodity;
        this.count = count;
        this.type = type;
    }
}
