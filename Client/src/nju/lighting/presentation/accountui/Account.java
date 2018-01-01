package nju.lighting.presentation.accountui;

import com.jfoenix.controls.JFXButton;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import nju.lighting.bl.accountbl.AccountBLService_Stub;
import nju.lighting.blservice.accountblservice.AccountBLService;
import nju.lighting.presentation.DialogUI.DialogHelper;
import nju.lighting.presentation.DialogUI.ValidateEventHandle;
import nju.lighting.presentation.factory.AccountBLServiceFactory;
import nju.lighting.presentation.utils.DateHelper;
import nju.lighting.vo.account.AccountLogVO;
import nju.lighting.vo.account.AccountVO;
import shared.ResultMessage;


import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created on 2017/12/26.
 * Description
 *
 * @author 陈俊宇
 */
public class Account implements Initializable {

    AccountBLService blService = AccountBLServiceFactory.getAccountBLService();

    @FXML
    HBox accountTableContainer;

    @FXML
    TableView<AccountLogVO> logTableView;

    @FXML
    TableColumn<AccountVO, String> accountName;

    @FXML
    TableColumn<AccountVO, Double> balance;

    @FXML
    TableColumn<AccountLogVO, String> time;

    @FXML
    TableColumn<AccountLogVO, Double> change;

    @FXML
    TableColumn<AccountLogVO, Double> theBalance;

    @FXML
    StackPane stackPane;

    @FXML
    JFXButton addBtn;


    ObservableList<AccountVO> accountVOObservableList;
    ObservableList<AccountLogVO> logVOObservableList = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AccountList.fxml"));
            accountTableContainer.getChildren().add(loader.load());
            AccountList listController = loader.getController();
            listController.setEditable(stackPane, logVOObservableList);
            accountVOObservableList = listController.getAccountVOObservableList();
        } catch (IOException e) {
            e.printStackTrace();
        }


        time.setCellValueFactory(c ->
                new SimpleStringProperty(DateHelper.accurateTime(c.getValue().getTime())));
        change.setCellValueFactory(c ->
                new SimpleDoubleProperty(c.getValue().getDelta()).asObject());
        theBalance.setCellValueFactory(c ->
                new SimpleDoubleProperty(c.getValue().getAmount()).asObject());
        change.setCellFactory(p -> {
            return new TableCell<AccountLogVO, Double>() {
                @Override
                protected void updateItem(Double item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        Label label;
                        if (item > 0) {
                            label=new Label("+"+getItem());
                            label.setTextFill(Color.GREEN);
                        } else {
                            label=new Label(getItem()+"");
                            label.setTextFill(Color.RED);
                        }
                        setGraphic(label);
                        setText(null);

                    }
                }
            };
        });

        logTableView.setItems(logVOObservableList);


        addBtn.setOnAction(e -> {

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AddAccount.fxml"));
                Node node = loader.load();
                AddAccount controller = loader.getController();

                ValidateEventHandle addEventHandle = new ValidateEventHandle() {
                    @Override
                    public boolean validate() {
                        if (controller.getAccount() != null) {
                            ResultMessage resultMessage = blService.addAccount(controller.getName(),
                                    controller.getAmount(),
                                    controller.getId());

                            if (resultMessage == ResultMessage.SUCCESS) {
                                {
                                    accountVOObservableList.add(controller.getAccount());
                                    return true;
                                }
                            } else
                                DialogHelper.dialog(resultMessage, stackPane);

                        }
                        return false;
                    }

                };

                DialogHelper.addDialog(node, stackPane, addEventHandle);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

    }

}
