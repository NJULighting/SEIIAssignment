package nju.lighting.presentation.utils;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import nju.lighting.presentation.DialogUI.DialogHelper;
import nju.lighting.presentation.DialogUI.ValidateEventHandle;
import nju.lighting.presentation.accountui.Account;
import nju.lighting.presentation.accountui.AccountList;
import nju.lighting.vo.account.AccountVO;

import java.io.IOException;

/**
 * Created on 2017/12/27.
 * Description
 *
 * @author 陈俊宇
 */
public class AccountHelper {





    public static void addAccount(StackPane stackPane,ObservableList<AccountVO> accountList){
        SimpleObjectProperty<AccountVO> account=new SimpleObjectProperty<>();
        loadAccount(stackPane,account);
        account.addListener(new ChangeListener<AccountVO>() {
            @Override
            public void changed(ObservableValue<? extends AccountVO> observable, AccountVO oldValue, AccountVO newValue) {
                accountList.add(account.getValue());
            }
        });
    }
    public static void addAccount(StackPane stackPane,SimpleObjectProperty<AccountVO> account){
        loadAccount(stackPane, account);
    }

    private static void loadAccount(StackPane stackPane, SimpleObjectProperty<AccountVO> account){
        FXMLLoader loader=new FXMLLoader(AccountHelper.class.getResource("../accountui/AccountList.fxml"));
        try {
            Node node =loader.load();
            AccountList controller=loader.getController();

            ValidateEventHandle eventHandle=new ValidateEventHandle() {
                @Override
                public boolean validate() {
                    if (controller.getAccount()==null)
                        return false;
                    else {
                        account.set(controller.getAccount());
                        return true;
                    }
                }
            };
            DialogHelper.addDialog(node,stackPane,eventHandle);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
