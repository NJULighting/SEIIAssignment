package nju.lighting.po.doc.lossandgaindoc;

import shared.LossAndGainItemType;

import java.io.Serializable;

public class LossAndGainItemPO implements Serializable {

    private static final long serialVersionUID = 7167744148385043685L;

    private int id;
    private String docId;
    private String commodityId;
    private int count;
    private LossAndGainItemType type;

    /**
     * Constructor for committing a new document.
     */
    public LossAndGainItemPO(String commodityId, int count, LossAndGainItemType type) {
        this.commodityId = commodityId;
        this.count = count;
        this.type = type;
    }

    /**
     * Constructor for approval module.
     */
    public LossAndGainItemPO(int id, String docId, String commodityId, int count, LossAndGainItemType type) {
        this.id = id;
        this.docId = docId;
        this.commodityId = commodityId;
        this.count = count;
        this.type = type;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public String getDocId() {
        return docId;
    }

    public String getCommodityId() {
        return commodityId;
    }

    public int getCount() {
        return count;
    }

    public LossAndGainItemType getType() {
        return type;
    }
}
