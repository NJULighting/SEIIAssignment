package nju.lighting.po.doc.alertdoc;

import javax.persistence.*;

@Entity
@Table(name = "ALERT_DOC_ITEM")
public class AlertDocItemPO {

    private int id;

    private String docId;

    private String commodityId;

    private int count;

    public AlertDocItemPO(String docId, String commodityId, int count) {
        this.docId = docId;
        this.commodityId = commodityId;
        this.count = count;
    }

    public AlertDocItemPO() {

    }

    public AlertDocItemPO(int id, String docId, String commodityId, int count) {
        this.id = id;
        this.docId = docId;
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
    public String getDocId() {
        return docId;
    }

    public void setDocId(String alertDocId) {
        this.docId = alertDocId;
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
