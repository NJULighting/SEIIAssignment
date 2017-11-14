package nju.lighting.bl.documentbl.lossandgaindoc;

import nju.lighting.bl.commoditybl.BasicCommodityItem;
import shared.LossAndGainItemType;

public class LossAndGainDocItem {

    private int count;

    private LossAndGainItemType type;

    private BasicCommodityItem commodityItem;

    public LossAndGainDocItem(int count, LossAndGainItemType type, BasicCommodityItem commodityItem) {
        this.count = count;
        this.type = type;
        this.commodityItem = commodityItem;
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

    public BasicCommodityItem getCommodityItem() {
        return commodityItem;
    }

    public void setCommodityItem(BasicCommodityItem commodityItem) {
        this.commodityItem = commodityItem;
    }
}
