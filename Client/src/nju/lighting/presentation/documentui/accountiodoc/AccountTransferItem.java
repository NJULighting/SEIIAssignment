package nju.lighting.presentation.documentui.accountiodoc;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import nju.lighting.presentation.accountui.Account;
import nju.lighting.presentation.factory.AccountBLServiceFactory;
import nju.lighting.vo.account.AccountVO;
import nju.lighting.vo.doc.accountiodoc.AccountTransferItemVO;

/**
 * Created on 2017/12/11.
 * Description 收付款单的小项的值
 * @author 陈俊宇
 */
public class AccountTransferItem {
    private SimpleStringProperty accountName = new SimpleStringProperty();
    private SimpleStringProperty comments = new SimpleStringProperty();
    private SimpleDoubleProperty amount = new SimpleDoubleProperty();
    private AccountVO accountVO;

    AccountTransferItem(AccountTransferItemVO vo) {
        accountVO=AccountBLServiceFactory.getAccountBLService().getAccount(vo.getAccountID());
        accountName.set(accountVO.getName());
        comments.set(vo.getComments());
        amount.set(vo.getAmount());

    }

    AccountTransferItem(AccountVO account) {
        accountVO=account;
        accountName.set(account.getName());
    }


    AccountTransferItemVO toTransferItem(){
        return new AccountTransferItemVO(amount.getValue(),comments.getValue(),accountVO.getId());
    }

    public String getAccountName() {
        return accountName.get();
    }

    SimpleStringProperty accountNameProperty() {
        return accountName;
    }

    public String getComments() {
        return comments.get();
    }

    SimpleStringProperty commentsProperty() {
        return comments;
    }

    public double getAmount() {
        return amount.get();
    }

    SimpleDoubleProperty amountProperty() {
        return amount;
    }

    public AccountVO getAccountVO() {
        return accountVO;
    }

    public void setAmount(double amount) {
        this.amount.set(amount);
    }
}
