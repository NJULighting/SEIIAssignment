package nju.lighting.bl.documentbl.accountiodoc;

import nju.lighting.bl.accountbl.AccountInfo;
import nju.lighting.bl.accountbl.AccountInfoImpl;
import nju.lighting.bl.documentbl.DocItem;
import nju.lighting.bl.documentbl.RedFlush;
import nju.lighting.po.doc.accountiodoc.AccountTransferItemPO;
import nju.lighting.vo.doc.accountiodoc.AccountTransferItemVO;
import shared.ResultMessage;

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
    private AccountIOType ioType;

    AccountDocItem(AccountTransferItemPO itemPO, AccountIOType ioType) {
        id = itemPO.getId();
        accountID = itemPO.getAccountID();
        amount = itemPO.getAmount();
        comments = itemPO.getComments();
        this.ioType = ioType;
    }

    AccountDocItem(AccountTransferItemVO itemVO, AccountIOType ioType) {
        accountID = itemVO.getAccountID();
        amount = itemVO.getAmount();
        comments = itemVO.getComments();
        this.ioType = ioType;
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
        id = 0;
        amount = -amount;
        comments = RedFlush.RED_FLUSH_COMMENT;
    }

    @Override
    public ResultMessage approve() {
        AccountInfo accountInfo = new AccountInfoImpl();
        // If the io type is IN, increase the account amount. Otherwise, decrease the account balance
        double amountChange = ioType == AccountIOType.IN ? amount : -amount;
        return accountInfo.updateAmount(accountID, amountChange);
    }
}
