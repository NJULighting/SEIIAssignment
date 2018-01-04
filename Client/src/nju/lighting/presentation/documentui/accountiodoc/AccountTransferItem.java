package nju.lighting.presentation.documentui.accountiodoc;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import nju.lighting.vo.account.AccountVO;
import nju.lighting.vo.doc.accountiodoc.AccountTransferItemVO;

/**
 * Created on 2017/12/11.
 * Description
 * @author 陈俊宇
 */
public class AccountTransferItem {
    SimpleStringProperty accountName = new SimpleStringProperty();
    SimpleStringProperty comments = new SimpleStringProperty();
    SimpleDoubleProperty amount = new SimpleDoubleProperty();

    public AccountTransferItem(AccountTransferItemVO vo) {
        accountName.set(vo.getAccountID());
        comments.set(vo.getComments());
        amount.set(vo.getAmount());

    }

    public AccountTransferItem(AccountVO account) {
        accountName.set(account.getName());

    }

    public String getAccountName() {
        return accountName.get();
    }

    public SimpleStringProperty accountNameProperty() {
        return accountName;
    }

    public String getComments() {
        return comments.get();
    }

    public SimpleStringProperty commentsProperty() {
        return comments;
    }

    public double getAmount() {
        return amount.get();
    }

    public SimpleDoubleProperty amountProperty() {
        return amount;
    }
}
