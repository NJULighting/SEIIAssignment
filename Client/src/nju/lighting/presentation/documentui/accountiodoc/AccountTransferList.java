package nju.lighting.presentation.documentui.accountiodoc;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
 *
 * @author 陈俊宇
 */
public class AccountTransferList implements Initializable {


    private ObservableList<AccountTransferItem> observableList=FXCollections.observableArrayList();

    @FXML
    TableColumn<AccountTransferItem, String> comments, account;

    @FXML
    TableColumn<AccountTransferItem, Double> amount;

    @FXML
    TableView accountItemTable;

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



    public void setEditable(){
        TableColumn delete=new TableColumn();
        delete.setCellFactory(new Callback<TableColumn, TableCell>() {
            @Override
            public TableCell call(TableColumn param) {
                return new BtnCell();
            }
        });
        accountItemTable.getColumns().add(delete);
        amount.setCellFactory(new Callback<TableColumn<AccountTransferItem, Double>, TableCell<AccountTransferItem, Double>>() {
            @Override
            public TableCell<AccountTransferItem, Double> call(TableColumn<AccountTransferItem, Double> param) {
                return new EditingCell<>("double");
            }
        });
        comments.setCellFactory(new Callback<TableColumn<AccountTransferItem, String>, TableCell<AccountTransferItem, String>>() {
            @Override
            public TableCell<AccountTransferItem, String> call(TableColumn<AccountTransferItem, String> param) {
                return new EditingCell<>("string");
            }
        });
    }
}
