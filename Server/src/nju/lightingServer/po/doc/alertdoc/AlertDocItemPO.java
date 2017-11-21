package nju.lightingServer.po.doc.alertdoc;


public class AlertDocItemPO {

    private String alertDocId;

    private String commodityId;

    private int count;

    public AlertDocItemPO(String alertDocId, String commodityId, int count) {
        this.alertDocId = alertDocId;
        this.commodityId = commodityId;
        this.count = count;
    }

    public String getAlertDocId() {
        return alertDocId;
    }

    public void setAlertDocId(String alertDocId) {
        this.alertDocId = alertDocId;
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
