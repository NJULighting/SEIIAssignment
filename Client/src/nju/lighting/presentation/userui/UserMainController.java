package nju.lighting.presentation.userui;

import com.jfoenix.controls.JFXButton;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import nju.lighting.blservice.userblservice.UserBLService;
import nju.lighting.presentation.DialogUI.DialogHelper;
import nju.lighting.presentation.factory.UserBLServiceFactory;
import nju.lighting.presentation.utils.TableViewHelper;
import nju.lighting.presentation.utils.UserHelper;
import nju.lighting.vo.UserVO;
import shared.Identity;
import shared.ResultMessage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserMainController implements Initializable {

    UserBLService blService = UserBLServiceFactory.getUserBLService();

    @FXML
    TableColumn<UserVO, String> userName, id, identity, authority;

    @FXML
    TableColumn button;

    @FXML
    TableView<UserVO> tableView;

    @FXML
    StackPane stackPane;

    @FXML
    JFXButton addUserBtn;

    private AddUser controller;

    private ObservableList<UserVO> userList = FXCollections.observableArrayList();

    @FXML
    void refresh() {
        userList.setAll(blService.getUserList());
    }


    private Node loadAddUser() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddUser.fxml"));
        try {
            Node node = loader.load();
            controller = loader.getController();
            return node;
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        userName.setCellValueFactory(c ->
                new SimpleStringProperty(c.getValue().getUsername()));

        id.setCellValueFactory(c ->
                new SimpleStringProperty(c.getValue().getID()));

        identity.setCellValueFactory(c ->
                new SimpleStringProperty(c.getValue().getIdentity().toString()));

        authority.setCellValueFactory(c ->
                new SimpleStringProperty(c.getValue().isAuthority() ? "最高权限" : "非最高权限"));

        tableView.setItems(userList);
        refresh();

        addUserBtn.setOnAction(e -> {
            Node node = loadAddUser();
            DialogHelper.addDialog(node, stackPane, controller.addUserHandler(userList));
        });

        button.setCellFactory(p ->
                new TableCell() {
                    @Override
                    protected void updateItem(Object item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setText(null);
                            setGraphic(null);
                        } else {
                            UserVO userVO = (UserVO) getTableView().getItems().get(getIndex());
                            JFXButton deleteBtn = new JFXButton("删除");
                            JFXButton modifyBtn = new JFXButton("修改");
                            HBox buttonBox = new HBox(modifyBtn, deleteBtn);
                            buttonBox.setSpacing(30);
                            modifyBtn.setOnAction(e -> {

                                Node node = loadAddUser();
                                DialogHelper.addDialog(node, stackPane, controller.changeHandler(
                                        userList, userVO));
                            });

                            deleteBtn.setOnAction(e -> {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("DeleteUser.fxml"));
                                try {
                                    Node node = loader.load();
                                    ((DeleteUser) loader.getController()).init(userVO);
                                    DialogHelper.addDialog(node, stackPane,
                                            new EventHandler() {
                                                @Override
                                                public void handle(Event event) {
                                                    ResultMessage resultMessage = blService.deleteUser(userVO.getID());
                                                    if (resultMessage==ResultMessage.SUCCESS)
                                                        userList.remove(userVO);
                                                    DialogHelper.dialog("删除用户",resultMessage, stackPane);
                                                }
                                            });
                                    ResultMessage resultMessage = blService.deleteUser(userVO.getID());
                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                }


                            });

                            if (userVO.getIdentity() == Identity.SYSTEM_ADMIN)
                                deleteBtn.setDisable(true);
                            TableViewHelper.setNodeVisibleOnlyMouseEntered(getTableRow(), buttonBox);
                            setGraphic(buttonBox);
                            setText(null);
                        }
                    }
                });


    }
}
