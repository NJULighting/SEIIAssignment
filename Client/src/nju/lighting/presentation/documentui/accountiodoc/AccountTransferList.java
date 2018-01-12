package nju.lighting.presentation.documentui.accountiodoc;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import nju.lighting.presentation.documentui.BtnCell;
import nju.lighting.presentation.documentui.EditingCell;
import nju.lighting.presentation.utils.TableViewHelper;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created on 2017/12/11.
 * Description
 * 收付款单的转账列表的 controller
 * @author 陈俊宇
 */
public class AccountTransferList implements Initializable {


    @FXML
    TableColumn<AccountTransferItem, String> comments, account;
    @FXML
    TableColumn<AccountTransferItem, Double> amount;
    @FXML
    TableView accountItemTable;
    private ObservableList<AccountTransferItem> observableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        comments.setCellValueFactory(cellData ->
                cellData.getValue().commentsProperty());
        account.setCellValueFactory(cellData ->
                cellData.getValue().accountNameProperty());
        amount.setCellValueFactory(cellData ->
                cellData.getValue().amountProperty().asObject());


        accountItemTable.setItems(observableList);


        TableViewHelper.commonSet(accountItemTable);


    }

    public ObservableList<AccountTransferItem> getObservableList() {
        return observableList;
    }


    public void setEditable() {
        TableColumn delete = new TableColumn();
        delete.setCellFactory(p-> new BtnCell());

        accountItemTable.getColumns().add(delete);

        amount.setCellFactory(p-> new  EditingCell<>("double"));

        comments.setCellFactory(p-> new EditingCell<>("string"));
    }
}
