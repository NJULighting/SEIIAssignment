package nju.lighting.po.doc.lossandgaindoc;


public class LossAndGainItemPO {

    private String lossAndGainDocId;

    private String commodityId;

    private int count;

    private LossAndGainItemType type;

    public LossAndGainItemPO(String lossAndGainDocId, String commodityId, int count, LossAndGainItemType type) {
        this.lossAndGainDocId = lossAndGainDocId;
        this.commodityId = commodityId;
        this.count = count;
        this.type = type;
    }

    public String getLossAndGainDocId() {
        return lossAndGainDocId;
    }

    public void setLossAndGainDocId(String lossAndGainDocId) {
        this.lossAndGainDocId = lossAndGainDocId;
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

    public LossAndGainItemType getType() {
        return type;
    }

    public void setType(LossAndGainItemType type) {
        this.type = type;
    }
}
