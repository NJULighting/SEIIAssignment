package nju.lighting.po.doc.accountiodoc;

import nju.lighting.po.doc.DocPO;
import shared.AccountIODocType;
import shared.DocState;
import shared.DocType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created on 2017/10/18.
 * Description:
 * @author Liao
 */
public class AccountOutDocPO extends DocPO implements Serializable {

    private static final long serialVersionUID = -56354721179246487L;
    private String customerID;
    private List<AccountTransferItemPO> transferAccountList;
    private double total;

    /**
     * Constructor for approval module
     */
    public AccountOutDocPO(String id, DocType docType, String userId, Date createTime,
                          Date checkTime, String approvalComment, DocState state, String approvalId,
                          AccountIODocType ioType, String customerID, List<AccountTransferItemPO> transferAccountList, double total) {
        super(id, docType, userId, createTime, checkTime, approvalComment, state, approvalId);
        this.customerID = customerID;
        this.transferAccountList = transferAccountList;
        this.total = total;
    }

    /**
     * Constructor for committing a new document.
     */
    public AccountOutDocPO(DocType type, String userId, Date createTime, AccountIODocType ioType,
                          String customerID, List<AccountTransferItemPO> transferAccountList, double total) {
        super(type, userId, createTime);
        this.customerID = customerID;
        this.transferAccountList = transferAccountList;
        this.total = total;
    }

    public void setIOType(AccountIODocType ioType) {
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public List<AccountTransferItemPO> getTransferAccountList() {
        return transferAccountList;
    }

    public void setTransferAccountList(ArrayList<AccountTransferItemPO> transferAccountList) {
        this.transferAccountList = transferAccountList;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
