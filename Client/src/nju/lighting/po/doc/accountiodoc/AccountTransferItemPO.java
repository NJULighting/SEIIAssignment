package nju.lighting.po.doc.accountiodoc;

import java.io.Serializable;

/**
 * Created on 2017/10/21.
 * Description:
 * @author Liao
 */
public class AccountTransferItemPO implements Serializable {

    private static final long serialVersionUID = -6496549992772237320L;
    private int id;
    private String accountID;
    private String docId;
    private double amount;
    private String comments;

    /**
     * Constructor for approval module
     */
    public AccountTransferItemPO(int id, String accountID, String docId, double amount, String comments) {
        this.id = id;
        this.accountID = accountID;
        this.docId = docId;
        this.amount = amount;
        this.comments = comments;
    }

    /**
     * Constructor for committing a new document.
     */
    public AccountTransferItemPO(String accountID, double amount, String comments) {
        this.accountID = accountID;
        this.amount = amount;
        this.comments = comments;
    }

    public String getDocId() {
        return docId;
    }

    public int getId() {
        return id;
    }

    public String getAccountID() {
        return accountID;
    }

    public double getAmount() {
        return amount;
    }

    public String getComments() {
        return comments;
    }
}
