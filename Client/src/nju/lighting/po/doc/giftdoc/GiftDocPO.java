package nju.lighting.po.doc.giftdoc;

import nju.lighting.po.doc.DocPO;
import shared.DocState;
import shared.DocType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created on 2017/10/19.
 * Description
 * @author 陈俊宇
 */
public class GiftDocPO extends DocPO {
    private static final long serialVersionUID = 4982735498710662541L;

    private List<GiftItemPO> giftItemPOs;
    private int customerID;
    private String repositoryID = "01";
    private double total;
    private int promotionId;

    /**
     * Constructor for approval module.
     */
    public GiftDocPO(String id, DocType docType, String userId, Date createTime, Date checkTime, String approvalComment, DocState state, String approvalId, List<GiftItemPO> giftItemPOs, int customerID, String repositoryID, double total, int promotionId) {
        super(id, docType, userId, createTime, checkTime, approvalComment, state, approvalId);
        this.giftItemPOs = giftItemPOs;
        this.customerID = customerID;
        this.repositoryID = repositoryID;
        this.total = total;
        this.promotionId = promotionId;
    }

    /**
     * Constructor for committing a new document.
     */
    public GiftDocPO(DocType type, String userId, Date createTime, List<GiftItemPO> giftItemPOs,
                     int customerID, String repositoryID, double total, int promotionId) {
        super(type, userId, createTime);
        this.giftItemPOs = giftItemPOs;
        this.customerID = customerID;
        this.repositoryID = repositoryID;
        this.total = total;
        this.promotionId = promotionId;
    }

    public List<GiftItemPO> getGiftItemPOs() {
        return giftItemPOs;
    }

    public int getCustomerID() {
        return customerID;
    }

    public String getRepositoryID() {
        return repositoryID;
    }

    public double getTotal() {
        return total;
    }

    public int getPromotionId() {
        return promotionId;
    }
}
