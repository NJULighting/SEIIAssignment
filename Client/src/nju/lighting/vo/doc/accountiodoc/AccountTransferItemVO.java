package nju.lighting.vo.doc.accountiodoc;

import nju.lighting.po.doc.accountiodoc.AccountTransferItemPO;

/**
 * Created on 2017/11/20.
 * Description:
 * @author Liao
 */
public class AccountTransferItemVO {
    private double amount;
    private String comments;
    private String accountID;

    public AccountTransferItemVO(double amount, String comments, String accountID) {
        this.amount = amount;
        this.comments = comments;
        this.accountID = accountID;
    }

    public double getAmount() {
        return amount;
    }

    public String getComments() {
        return comments;
    }

    public String getAccountID() {
        return accountID;
    }

    AccountTransferItemPO toPO() {
        return new AccountTransferItemPO(accountID, amount, comments);
    }
}
