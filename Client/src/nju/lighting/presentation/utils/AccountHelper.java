package nju.lighting.presentation.utils;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import nju.lighting.presentation.DialogUI.DialogHelper;
import nju.lighting.presentation.DialogUI.ValidateEventHandle;
import nju.lighting.presentation.accountui.AccountList;
import nju.lighting.presentation.mainui.MainUI;
import nju.lighting.vo.account.AccountVO;

import java.io.IOException;

/**
 * Created on 2017/12/27.
 * Description
 *
 * @author 陈俊宇
 */
public class AccountHelper {


    public static void addAccount(ObservableList<AccountVO> accountList) {
        SimpleObjectProperty<AccountVO> account = new SimpleObjectProperty<>();
        loadAccount(account);
        account.addListener(new ChangeListener<AccountVO>() {
            @Override
            public void changed(ObservableValue<? extends AccountVO> observable, AccountVO oldValue, AccountVO newValue) {
                accountList.add(account.getValue());
            }
        });
    }

    public static void addAccount(SimpleObjectProperty<AccountVO> account) {
        loadAccount(account);
    }

    private static void loadAccount(SimpleObjectProperty<AccountVO> account) {
        FXMLLoader loader = new FXMLLoader(AccountHelper.class.getResource("../accountui/AccountList.fxml"));
        try {
            Node node = loader.load();
            AccountList controller = loader.getController();

            ValidateEventHandle eventHandle = new ValidateEventHandle() {
                @Override
                public boolean validate() {
                    if (controller.getAccount() == null)
                        return false;
                    else {
                        account.set(controller.getAccount());
                        return true;
                    }
                }
            };
            DialogHelper.addDialog(node, MainUI.getStackPane(), eventHandle);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
