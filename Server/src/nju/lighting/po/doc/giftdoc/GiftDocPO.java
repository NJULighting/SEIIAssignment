package nju.lighting.po.doc.giftdoc;

import nju.lighting.po.doc.DocPO;
import shared.DocState;
import shared.DocType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created on 2017/10/19.
 * Description
 * @author 陈俊宇
 */
@Entity
@Table(name = "GIFT_DOC")
public class GiftDocPO extends DocPO {

    private ArrayList<GiftItemPO> giftItemPOs;

    private int customerID;

    private String repositoryID = "01";

    private double total;

    private int promotionId;

    public GiftDocPO() {
    }

    public GiftDocPO(String id, DocType docType, String userId,
                     Date createTime, Date checkTime,
                     String approvalComment, DocState state, String approvalId,
                     ArrayList<GiftItemPO> giftItemPOs, int customerID,
                     String repositoryID, double total, int promotionId) {
        super(id, docType, userId, createTime, checkTime, approvalComment, state, approvalId);
        this.giftItemPOs = giftItemPOs;
        this.customerID = customerID;
        this.repositoryID = repositoryID;
        this.total = total;
        this.promotionId = promotionId;
    }

    @Transient
    public ArrayList<GiftItemPO> getGifts() {
        return giftItemPOs;
    }

    public void setGifts(ArrayList<GiftItemPO> giftItemPOs) {
        this.giftItemPOs = giftItemPOs;
    }

    @Column(name = "REPOSITORY_ID", length = 5)
    public String getRepositoryID() {
        return repositoryID;
    }

    public void setRepositoryID(String repository) {
        this.repositoryID = repository;
    }

    @Transient
    public ArrayList<GiftItemPO> getGiftItemPOs() {
        return giftItemPOs;
    }

    public void setGiftItemPOs(ArrayList<GiftItemPO> giftItemPOs) {
        this.giftItemPOs = giftItemPOs;
    }

    @Column(name = "PROMOTION_ID", nullable = false)
    public int getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(int promotionId) {
        this.promotionId = promotionId;
    }

    @Column(name = "CUSTOMER_ID", nullable = false)
    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customer) {
        this.customerID = customer;
    }

    @Column(name = "TOTAL", nullable = false)
    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
