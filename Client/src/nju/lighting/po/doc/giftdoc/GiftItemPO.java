package nju.lighting.po.doc.giftdoc;

import java.io.Serializable;

/**
 * Created on 2017/10/22.
 * Description
 * @author 陈俊宇
 */
public class GiftItemPO implements Serializable {
    private static final long serialVersionUID = 8168210972699713697L;

    private int id;
    private String commodityID;
    private int count;
    private double subtotal;
    private String docId;


    /**
     * Constructor for committing a new document.
     */
    public GiftItemPO(String commodityID, int count, double subtotal) {
        this.commodityID = commodityID;
        this.count = count;
        this.subtotal = subtotal;
    }

    /**
     * Constructor for approval module.
     */
    public GiftItemPO(int id, String commodityID, int count, double subtotal, String docId) {
        this.id = id;
        this.commodityID = commodityID;
        this.count = count;
        this.subtotal = subtotal;
        this.docId = docId;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public String getCommodityID() {
        return commodityID;
    }

    public int getCount() {
        return count;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public String getDocId() {
        return docId;
    }
}
