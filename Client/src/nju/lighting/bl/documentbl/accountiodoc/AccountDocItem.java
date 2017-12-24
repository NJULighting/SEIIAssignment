package nju.lighting.bl.documentbl.accountiodoc;

import nju.lighting.bl.documentbl.DocItem;
import nju.lighting.po.doc.accountiodoc.AccountTransferItemPO;
import nju.lighting.vo.doc.accountiodoc.AccountTransferItemVO;

/**
 * Created on 2017/12/14.
 * Description:
 * @author Liao
 */
class AccountDocItem implements DocItem {
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

    AccountDocItem(AccountTransferItemVO itemVO) {
        accountID = itemVO.getAccountID();
        amount = itemVO.getAmount();
        comments = itemVO.getComments();
    }

    int getId() {
        return id;
    }

    String getAccountID() {
        return accountID;
    }

    double getAmount() {
        return amount;
    }

    String getComments() {
        return comments;
    }

    AccountTransferItemPO toPO(String docId) {
        return new AccountTransferItemPO(id, accountID, docId, amount, comments);
    }

    AccountTransferItemVO toVO() {
        return new AccountTransferItemVO(amount, comments, accountID, id);
    }

    @Override
    public void redFlush() {

    }

    @Override
    public void approve() {

    }
}
