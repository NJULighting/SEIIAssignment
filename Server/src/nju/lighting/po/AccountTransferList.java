package nju.lighting.po;

/**
 * Created on 2017/10/21.
 * Description:
 * @author Liao
 */
public class AccountTransferList {
    private String account;
    private int amount;
    private String comments;

    public AccountTransferList(String account, int amount, String comments) {
        this.account = account;
        this.amount = amount;
        this.comments = comments;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
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
