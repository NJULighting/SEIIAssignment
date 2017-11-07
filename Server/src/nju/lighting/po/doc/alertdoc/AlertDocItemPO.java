package nju.lighting.po.doc.alertdoc;


public class AlertDocItemPO {

    private String userId;

    private String alertDocId;

    private String commodityId;

    private int count;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public AlertDocItemPO(String userId, String alertDocId, String commodityId, int count) {
        this.userId = userId;
        this.alertDocId = alertDocId;
        this.commodityId = commodityId;
        this.count = count;
    }
}
