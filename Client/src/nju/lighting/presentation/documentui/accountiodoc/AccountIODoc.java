package nju.lighting.presentation.documentui.accountiodoc;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import nju.lighting.presentation.documentui.Doc;
import nju.lighting.vo.doc.accountiodoc.AccountIODocVO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Created on 2017/12/11.
 * Description
 * @author 陈俊宇
 */
public class AccountIODoc implements Initializable {

    AccountIODocVO accountIODocVO;

    @FXML
    HBox listBox;

    @FXML
    Label creator, customer, id, total;

    public AccountIODoc() {
        accountIODocVO = (AccountIODocVO) Doc.doc;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        creator.setText(accountIODocVO.getCreatorId());
//        customer.setText(accountIODocVO.getCustomer());
//        id.setText(accountIODocVO.getDocId());
//        total.setText(accountIODocVO.getTotal()+"");


        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AccountTransferList.fxml"));
            listBox.getChildren().add(loader.load());
            AccountTransferList controller = loader.getController();
            controller.getObservableList().addAll(
                    accountIODocVO.getTransferAccountList().stream()
                            .map(x -> new AccountTransferItem(x))
                            .collect(Collectors.toList())
            );

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
