package nju.lighting.presentation.documentui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import nju.lighting.vo.doc.accountiodoc.AccountIODocVO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created on 2017/12/11.
 * Description
 *
 * @author 陈俊宇
 */
public class AccountIODoc implements Initializable {

    AccountIODocVO accountIODocVO;

    @FXML
    HBox listBox;

    @FXML
    Label creator,customer,id,total;

    public AccountIODoc(){
        accountIODocVO=(AccountIODocVO)Doc.doc;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        creator.setText(accountIODocVO.getCreatorId());
//        customer.setText(accountIODocVO.getCustomer());
//        id.setText(accountIODocVO.getDocId());
//        total.setText(accountIODocVO.getTotal()+"");

        AccountTransferList.setAccountTransferItemVOList(accountIODocVO.getTransferAccountList());
        try {
            listBox.getChildren().add(FXMLLoader.load(getClass().getResource("AccountTransferList.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
