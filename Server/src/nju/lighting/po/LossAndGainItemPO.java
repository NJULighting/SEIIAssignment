package nju.lighting.po;

public class LossAndGainItemPO extends DocItemPO {

    public static final String LOSS = "LOSS";

    public static final String GAIN = "GAIN";

    private String type;
    private String commodityId;
    private int count;

    public LossAndGainItemPO(String commodityId, int count, String type) {
        this.commodityId = commodityId;
        this.count = count;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
