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

    /**
     * 选择客户按钮注册的方法，加载选择用户界面以及将给界面的确认按钮注册方法
     * @param account 调用者的property属性，可以通过对他的setValue 来触发调用者的一系列操作
     */
    public static void addAccount(SimpleObjectProperty<AccountVO> account) {
        loadAccount(account);
    }


    private static void loadAccount(SimpleObjectProperty<AccountVO> account) {
        FXMLLoader loader = new FXMLLoader(AccountHelper.class.getClassLoader().getResource("nju/lighting/presentation/accountui/AccountList.fxml"));
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
