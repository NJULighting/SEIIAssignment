package nju.lighting.vo;

public class LossAndGainItemVO extends DocItemVO {

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

    public void setCount(int count) {
        this.count = count;
    }

    public LossAndGainItemVO(String commodityId, int count) {
        this.commodityId = commodityId;
        this.count = count;
    }
}
