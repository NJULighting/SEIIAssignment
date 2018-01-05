package nju.lighting.presentation.documentui.accountiodoc;

import com.jfoenix.controls.JFXToggleButton;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import nju.lighting.presentation.mainui.Upper;
import nju.lighting.presentation.utils.AccountHelper;
import nju.lighting.presentation.utils.CustomerHelper;
import nju.lighting.vo.CustomerVO;
import nju.lighting.vo.account.AccountVO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created on 2017/12/26.
 * Description
 * @author 陈俊宇
 */
public class AddAccountIODoc implements Initializable, Upper {

    @FXML
    JFXToggleButton toggleBtn;

    @FXML
    Pane mainPane;

    @FXML
    HBox container, tableContainer;

    @FXML
    Label sub;

    @FXML
    TextField customerText;

    @FXML
    Button addTransferItemBtn;

    @FXML
    StackPane stackPane;

    SimpleObjectProperty<AccountVO> accountProperty = new SimpleObjectProperty<>();
    ObservableList<AccountTransferItem> itemList;


    SimpleObjectProperty<CustomerVO> customerProperty = new SimpleObjectProperty<>();

    @FXML
    void commit() {

    }

    @FXML
    void chooseCustomer() {
        CustomerHelper.setCustomer(this, customerProperty);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AccountTransferList.fxml"));
            tableContainer.getChildren().add(loader.load());
            AccountTransferList controller = loader.getController();
            controller.setEditable();
            itemList = controller.getObservableList();
        } catch (IOException e) {
            e.printStackTrace();
        }

        accountProperty.addListener(new ChangeListener<AccountVO>() {
            @Override
            public void changed(ObservableValue<? extends AccountVO> observable, AccountVO oldValue, AccountVO newValue) {
                itemList.add(new AccountTransferItem(newValue));
            }
        });

        addTransferItemBtn.setOnAction(e -> {
            AccountHelper.addAccount(stackPane, accountProperty);
        });

        customerProperty.addListener(new ChangeListener<CustomerVO>() {
            @Override
            public void changed(ObservableValue<? extends CustomerVO> observable, CustomerVO oldValue, CustomerVO newValue) {
                customerText.setText(customerProperty.getValue().getName());
            }
        });

        toggleBtn.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    toggleBtn.setText("收款单");
                } else
                    toggleBtn.setText("付款单");
            }
        });
    }


    public void back() {
        setChildren(mainPane, "");
    }

    public void setChildren(Node node, String title) {
        container.getChildren().setAll(node);
        sub.setText(title);
    }


}
