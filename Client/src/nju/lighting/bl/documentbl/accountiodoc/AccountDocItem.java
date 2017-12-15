package nju.lighting.bl.documentbl.accountiodoc;

import nju.lighting.po.doc.accountiodoc.AccountTransferItemPO;

/**
 * Created on 2017/12/14.
 * Description:
 * @author Liao
 */
class AccountDocItem {
    private int id;
    private String accountID;
    private double amount;
    private String comments;

    AccountDocItem(AccountTransferItemPO itemPO) {
        id = itemPO.getId();
        accountID = itemPO.getAccountID();
        amount = itemPO.getAmount();
        comments = itemPO.getComments();
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

    AccountTransferItemPO toPO(String docId) {
        return new AccountTransferItemPO(id, accountID, docId, amount, comments);
    }
}
