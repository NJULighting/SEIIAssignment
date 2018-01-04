package nju.lighting.presentation.documentui.costdoc;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import nju.lighting.blservice.accountblservice.AccountBLService;
import nju.lighting.presentation.documentui.EditingCell;
import nju.lighting.presentation.factory.AccountBLServiceFactory;
import nju.lighting.vo.doc.costdoc.CostDocItemVO;
import shared.CostDocItemType;

import java.net.URL;
import java.util.ResourceBundle;

public class CostDocListController implements Initializable{

    private AccountBLService accountBLService = AccountBLServiceFactory.getAccountBLService();

    @FXML
    private TableView<CostDocItemVO> tableView;

    @FXML
    private Button addBtn;

    @FXML
    private Button finishBtn;

    @FXML
    private Button accountBtn;

    @FXML
    private JFXTextField account,amount,afterDis,total;

    @FXML
    private TableColumn<CostDocItemVO, CostDocItemType> type;

    @FXML
    private TableColumn<CostDocItemVO, Double> amountT;

    @FXML
    private TableColumn<CostDocItemVO, String> remarks;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void add(){

        Callback<TableColumn<CostDocItemVO, String>,
                                        TableCell<CostDocItemVO, String>> cellFactory
                = (TableColumn<CostDocItemVO, String> p) -> new EditingCell<>("string");


        remarks.setCellValueFactory(
                new PropertyValueFactory<>("firstName"));
        remarks.setCellFactory(cellFactory);
        remarks.setOnEditCommit(
                (TableColumn.CellEditEvent<CostDocItemVO, String> t) -> {
                    ((CostDocItemVO) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setComment(t.getNewValue());
                });
    }
}
