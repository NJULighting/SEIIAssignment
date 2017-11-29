package nju.lighting.po.doc.alertdoc;

import javax.persistence.*;

@Entity
@Table(name = "ALERT_DOC_ITEM")
public class AlertDocItemPO {

    private int id;

    private String alertDocId;

    private String commodityId;

    private int count;

    public AlertDocItemPO(String alertDocId, String commodityId, int count) {
        this.alertDocId = alertDocId;
        this.commodityId = commodityId;
        this.count = count;
    }

    public AlertDocItemPO() {

    }

    public AlertDocItemPO(int id, String alertDocId, String commodityId, int count) {
        this.id = id;
        this.alertDocId = alertDocId;
        this.commodityId = commodityId;
        this.count = count;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "ALERT_DOC_ID", nullable = false, length = 20)
    public String getAlertDocId() {
        return alertDocId;
    }

    public void setAlertDocId(String alertDocId) {
        this.alertDocId = alertDocId;
    }

    @Column(name = "COMMODITY_ID", nullable = false, length = 36)
    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
    }

    @Column(name = "COUNT", nullable = false)
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
