package nju.lighting.po.doc.giftdoc;

import shared.Item;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created on 2017/10/22.
 * Description
 * @author 陈俊宇
 */
@Entity
@Table(name = "GIFT_DOC_ITEM")
public class GiftItemPO implements Item, Serializable {

    private static final long serialVersionUID = 8168210972699713697L;

    private int id;
    private String commodityID;
    private int count;
    private double subtotal;
    private String docId;

    @Column(name = "GIFT_DOC_ID", nullable = false, length = 36)
    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public GiftItemPO() {

    }

    public GiftItemPO(int id, String commodityID, int count, double subtotal) {
        this.id = id;
        this.commodityID = commodityID;
        this.count = count;
        this.subtotal = subtotal;
    }

    public GiftItemPO(String commodityID, int count, double subtotal) {
        this.commodityID = commodityID;
        this.count = count;
        this.subtotal = subtotal;
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

    @Column(name = "COMMODITY_ID", length = 36, nullable = false)
    public String getCommodityID() {
        return commodityID;
    }

    public void setCommodityID(String commodity) {
        this.commodityID = commodity;
    }

    @Column(name = "COUNT", nullable = false)
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Column(name = "SUB_TOTAL", nullable = false)
    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
}
