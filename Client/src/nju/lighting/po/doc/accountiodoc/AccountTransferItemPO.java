package nju.lighting.po.doc.accountiodoc;

import shared.Item;

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

    public AccountTransferItemPO(int id, String accountID, String docId, double amount, String comments) {
        this.id = id;
        this.accountID = accountID;
        this.docId = docId;
        this.amount = amount;
        this.comments = comments;
    }

    public int getId() {
        return id;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
