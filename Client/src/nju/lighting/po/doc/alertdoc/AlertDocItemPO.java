package nju.lighting.po.doc.alertdoc;


import java.io.Serializable;

public class AlertDocItemPO implements Serializable {

    private static final long serialVersionUID = 5024994132501068584L;
    private int id;
    private String docId;
    private String commodityId;
    private int count;

    /**
     * Constructor for committing a new document
     */
    public AlertDocItemPO(String commodityId, int count) {
        this.docId = docId;
        this.commodityId = commodityId;
        this.count = count;
    }

    /**
     * Constructor for approval module
     */
    public AlertDocItemPO(int id, String docId, String commodityId, int count) {
        this.id = id;
        this.docId = docId;
        this.commodityId = commodityId;
        this.count = count;
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
}
