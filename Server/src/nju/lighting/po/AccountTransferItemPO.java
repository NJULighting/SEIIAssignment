package nju.lighting.po;

/**
 * Created on 2017/10/21.
 * Description:
 * @author Liao
 */
public class AccountTransferItemPO {
    private String id;
    private String accountID;
    private int amount;
    private String comments;
    public AccountTransferItemPO(String accountID, int amount, String comments) {
        this.accountID = accountID;
        this.amount = amount;
        this.comments = comments;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
