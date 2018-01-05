package nju.lighting.po.doc.costdoc;

import nju.lighting.po.doc.DocPO;
import shared.DocState;
import shared.DocType;

import java.util.Date;
import java.util.List;

/**
 * Created on 2017/10/18.
 * Description:
 * @author Liao
 */
public class CostDocPO extends DocPO {
    private static final long serialVersionUID = -7793771039130081796L;
    private String accountID;
    private List<CostDocItemPO> itemList;
    private double total;

    /**
     * Constructor for committing a new document.
     */
    public CostDocPO(DocType type, String userId, Date createTime, String accountID,
                     List<CostDocItemPO> itemList, double total) {
        super(type, userId, createTime);
        this.accountID = accountID;
        this.itemList = itemList;
        this.total = total;
    }

    /**
     * Constructor for approval module.
     */
    public CostDocPO(String id, DocType docType, String userId, Date createTime,
                     Date checkTime, String approvalComment, DocState state, String approvalId,
                     String accountID, List<CostDocItemPO> itemList, double total) {
        super(id, docType, userId, createTime, checkTime, approvalComment, state, approvalId);
        this.accountID = accountID;
        this.itemList = itemList;
        this.total = total;
    }

    public String getAccountID() {
        return accountID;
    }

    public List<CostDocItemPO> getItemList() {
        return itemList;
    }

    public double getTotal() {
        return total;
    }
}
