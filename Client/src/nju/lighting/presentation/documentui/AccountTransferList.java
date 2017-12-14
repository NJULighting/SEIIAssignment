package nju.lighting.presentation.documentui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import nju.lighting.presentation.utils.TableViewHelper;
import nju.lighting.vo.doc.accountiodoc.AccountTransferItemVO;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Created on 2017/12/11.
 * Description
 * 收付款单的转账列表的 controller
 *
 * @author 陈俊宇
 */
public class AccountTransferList implements Initializable {
    private static List<AccountTransferItemVO> accountTransferItemVOList;

    public static void setAccountTransferItemVOList(
            List<AccountTransferItemVO> accountTransferItemVOList) {
        AccountTransferList.accountTransferItemVOList = accountTransferItemVOList;
    }

    @FXML
    TableColumn<AccountTransferItem, String> comments, account;

    @FXML
    TableColumn<AccountTransferItem, Double> amount;

    @FXML
    TableView accountItemTable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ObservableList itemObservableList = FXCollections.observableArrayList();

        comments.setCellValueFactory(cellData ->
                cellData.getValue().commentsProperty());
        account.setCellValueFactory(cellData ->
                cellData.getValue().accountNameProperty());
        amount.setCellValueFactory(cellData ->
                cellData.getValue().amountProperty().asObject());


        itemObservableList.addAll(
                accountTransferItemVOList.stream()
                        .map(x -> new AccountTransferItem(x))
                        .collect(Collectors.toList()));

        accountItemTable.setItems(itemObservableList);


        System.out.println(accountItemTable.getItems());
        System.out.println(comments);


        TableViewHelper.commonSet(accountItemTable);


    }
}
