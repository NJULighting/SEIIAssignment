package nju.lighting.vo.doc.lossandgaindoc;

import nju.lighting.po.doc.lossandgaindoc.LossAndGainItemPO;
import nju.lighting.vo.commodity.BasicCommodityItemVO;
import shared.LossAndGainItemType;

public class LossAndGainDocItemVO {

    private BasicCommodityItemVO commodity;
    private int count;
    private LossAndGainItemType type;

    public LossAndGainDocItemVO(BasicCommodityItemVO commodity, int count, LossAndGainItemType type) {
        this.commodity = commodity;
        this.count = count;
        this.type = type;
    }

    public BasicCommodityItemVO getCommodity() {
        return commodity;
    }

    public int getCount() {
        return count;
    }

    public LossAndGainItemType getType() {
        return type;
    }

    LossAndGainItemPO toPO() {
        return new LossAndGainItemPO(commodity.getId(), count, type);
    }
}
