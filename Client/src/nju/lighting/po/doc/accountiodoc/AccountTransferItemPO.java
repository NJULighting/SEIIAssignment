package nju.lighting.po.doc.accountiodoc;

/**
 * Created on 2017/10/21.
 * Description:
 * @author Liao
 */
public class AccountTransferItemPO {
    private String id;
    private String accountID;
    private String accountIODocID;
    private double amount;
    private String comments;

    public AccountTransferItemPO(String id, String accountID, String accountIODocID, double amount, String comments) {
        this.id = id;
        this.accountID = accountID;
        this.accountIODocID = accountIODocID;
        this.amount = amount;
        this.comments = comments;
    }

    public String getAccountIODocID() {
        return accountIODocID;
    }

    public void setAccountIODocID(String accountIODocID) {
        this.accountIODocID = accountIODocID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
