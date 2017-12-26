package nju.lighting.presentation.documentui.accountiodoc;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import nju.lighting.vo.doc.accountiodoc.AccountTransferItemVO;

/**
 * Created on 2017/12/11.
 * Description
 *
 * @author 陈俊宇
 */
public class AccountTransferItem {
    SimpleStringProperty accountName;
    SimpleStringProperty comments;
    SimpleDoubleProperty amount;

    public AccountTransferItem(AccountTransferItemVO vo) {
        accountName = new SimpleStringProperty(vo.getAccountID());
        comments = new SimpleStringProperty(vo.getComments());
        amount = new SimpleDoubleProperty(vo.getAmount());

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
