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
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import nju.lighting.bl.accountbl.AccountBLService_Stub;
import nju.lighting.blservice.accountblservice.AccountBLService;
import nju.lighting.presentation.DialogUI.DialogHelper;
import nju.lighting.presentation.DialogUI.ValidateEventHandle;
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

    AccountBLService blService = new AccountBLService_Stub();

    @FXML
    TableView<AccountVO> accountTableView;

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
    @FXML
    Button searchBtn;

    @FXML
    TextField searchText;

    @FXML
    void search() {
        String keyWord = searchText.getText();

        if (keyWord != null && keyWord.length() != 0) {

            List<AccountVO> result = blService.findAccounts(keyWord);
            if (result != null)
                accountVOObservableList.setAll(result);
            else accountVOObservableList.clear();
            logVOObservableList.clear();
        } else
            accountVOObservableList.setAll(blService.getAccountList());

    }

    ObservableList<AccountVO> accountVOObservableList = FXCollections.observableArrayList();
    ObservableList<AccountLogVO> logVOObservableList = FXCollections.observableArrayList();

    List<AccountVO> completeAccountList = blService.getAccountList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        searchBtn.setOnAction(e->{search();});
        searchText.setOnAction(e->{search();});

        //表格数据初始化
        accountVOObservableList.addAll(completeAccountList);

        accountName.setCellValueFactory(x ->
                new SimpleStringProperty(x.getValue().getName()));

        balance.setCellValueFactory((x ->
                new SimpleDoubleProperty(x.getValue().getAmount()).asObject()));

        accountTableView.setItems(accountVOObservableList);

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
                        setGraphic(null);
                        if (item > 0) {
                            setText("+" + getItem());
                            setTextFill(Color.GREEN);
                        } else {
                            setText("-" + Math.abs(getItem()));
                            setTextFill(Color.RED);
                        }

                    }
                }
            };
        });

        logTableView.setItems(logVOObservableList);

        //账户列表右击菜单栏
        //删除按钮
        MenuItem delete = new MenuItem("删除");
        delete.setOnAction(e -> {
            AccountVO accountVO = accountTableView.getSelectionModel().getSelectedItem();
            EventHandler deleteHandle = new EventHandler() {
                @Override
                public void handle(Event event) {
                    ResultMessage resultMessage = blService.deleteAccount(accountVO.getId());
                    System.out.println("delete Account id:" + accountVO.getId());
                    if (resultMessage == ResultMessage.SUCCESS) {
                        accountVOObservableList.remove(accountVO);
                    } else
                        DialogHelper.dialog(resultMessage, stackPane);
                }
            };
            DialogHelper.addDialog("你确定要删除账户" + accountVO.getName() + "?",
                    stackPane, deleteHandle);
        });
        MenuItem modify = new MenuItem("修改账户");
        modify.setOnAction(e -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddAccount.fxml"));
            try {
                Node node = loader.load();
                AddAccount controller = loader.getController();
                controller.setModify();

                ValidateEventHandle modifyHandle = new ValidateEventHandle() {
                    @Override
                    public boolean validate() {
                        String id = controller.getId(), name = controller.getName();
                        ResultMessage resultMessage = blService.modifyAccount(id, name);
                        if (resultMessage == ResultMessage.SUCCESS) {
                            int index = accountTableView.getSelectionModel().getSelectedIndex();
                            AccountVO accountVO = accountTableView.getItems().get(index);
                            accountVO.setId(id);
                            accountVO.setName(name);
                            accountTableView.getItems().set(index,
                                    accountVO);
                            return true;
                        } else
                            DialogHelper.dialog(resultMessage, stackPane);

                        return false;
                    }
                };

                DialogHelper.addDialog(node, stackPane, modifyHandle);
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        });
        accountTableView.setContextMenu(new ContextMenu(delete, modify));


        //账户列表左击显示具体条目
        accountTableView.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                System.out.println("clicked");
                if (!accountTableView.getSelectionModel().isEmpty()) {
                    List<AccountLogVO> list = accountTableView.getSelectionModel().getSelectedItem().getAccountLogs();
                    if (list != null)
                        logVOObservableList.setAll(list);
                    else
                        logVOObservableList.clear();

                }

            }
        });


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
