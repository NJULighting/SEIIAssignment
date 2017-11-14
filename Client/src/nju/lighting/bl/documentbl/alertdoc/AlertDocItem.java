package nju.lighting.bl.documentbl.alertdoc;

import nju.lighting.bl.commoditybl.BasicCommodityItem;

public class AlertDocItem {

    private int count;

    private BasicCommodityItem commodityItem;

    public AlertDocItem(int count, BasicCommodityItem commodityItem) {
        this.count = count;
        this.commodityItem = commodityItem;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public BasicCommodityItem getCommodityItem() {
        return commodityItem;
    }

    public void setCommodityItem(BasicCommodityItem commodityItem) {
        this.commodityItem = commodityItem;
    }
}
