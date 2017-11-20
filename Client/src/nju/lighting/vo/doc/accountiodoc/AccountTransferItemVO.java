package nju.lighting.vo.doc.accountiodoc;

/**
 * Created on 2017/11/20.
 * Description:
 * @author Liao
 */
public class AccountTransferItemVO {
    private double amount;
    private String comments;
    private String accountName;

    public AccountTransferItemVO(double amount, String comments, String accountName) {
        this.amount = amount;
        this.comments = comments;
        this.accountName = accountName;
    }

    public double getAmount() {
        return amount;
    }

    public String getComments() {
        return comments;
    }

    public String getAccountName() {
        return accountName;
    }
}
