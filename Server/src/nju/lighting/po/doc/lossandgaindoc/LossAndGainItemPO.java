package nju.lighting.po.doc.lossandgaindoc;

import shared.LossAndGainItemType;

import javax.persistence.*;

@Entity
@Table(name = "LOSS_AND_GAIN_DOC_ITEM")
public class LossAndGainItemPO {

    private int id;

    private String docId;

    private String commodityId;

    private int count;

    private LossAndGainItemType type;

    public LossAndGainItemPO() {

    }

    public LossAndGainItemPO(String docId, String commodityId, int count, LossAndGainItemType type) {
        this.docId = docId;
        this.commodityId = commodityId;
        this.count = count;
        this.type = type;
    }

    public LossAndGainItemPO(int id, String docId, String commodityId, int count, LossAndGainItemType type) {
        this.id = id;
        this.docId = docId;
        this.commodityId = commodityId;
        this.count = count;
        this.type = type;
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

    @Column(name = "LOSS_AND_GAIN_DOC_ID", nullable = false, length = 20)
    public String getDocId() {
        return docId;
    }

    public void setDocId(String lossAndGainDocId) {
        this.docId = lossAndGainDocId;
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

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE", nullable = false, length = 20)
    public LossAndGainItemType getType() {
        return type;
    }

    public void setType(LossAndGainItemType type) {
        this.type = type;
    }
}
