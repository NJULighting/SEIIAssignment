package nju.lighting.vo;

public class AlertDocItemVO extends DocItemVO {

    private String commodityId;

    private int count;

    public AlertDocItemVO(String commodityId, int count) {
        this.commodityId = commodityId;
        this.count = count;
    }

    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

