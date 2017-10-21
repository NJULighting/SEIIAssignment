package nju.lighting.po;

public class AlertDocItemPO extends DocItemPO{

    private String commodityId;

    private int count;

    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
    }

    public int getCount() {
        return count;
    }

    public AlertDocItemPO(String commodityId, int count) {
        this.commodityId = commodityId;
        this.count = count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

