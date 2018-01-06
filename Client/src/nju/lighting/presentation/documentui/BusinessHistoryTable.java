package nju.lighting.presentation.documentui;

import com.jfoenix.controls.*;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import nju.lighting.blservice.documentblservice.DocBLService;
import nju.lighting.presentation.factory.DocBLServiceFactory;
import nju.lighting.presentation.mainui.Upper;
import nju.lighting.presentation.utils.DateHelper;
import nju.lighting.presentation.utils.DocHelper;
import nju.lighting.presentation.utils.TableViewHelper;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.viewtables.BusinessHistoryItemVO;
import shared.DocumentFilter;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Created on 2018/1/5.
 * Description
 *
 * @author 陈俊宇
 */
public class BusinessHistoryTable implements Initializable, Upper {

    @FXML
    TableColumn<BusinessHistoryItemVO, String> type, customer, creator, time, repository;

    @FXML
    TableColumn btn;

    @FXML
    TableView<BusinessHistoryItemVO> tableView;

    @FXML
    JFXDatePicker startDate, endDate;

    @FXML
    JFXComboBox typeBox, creatorBox, repositoryBox;

    @FXML
    JFXTextField customerText;

    @FXML
    HBox container;

    @FXML
    AnchorPane mainPane;

    @FXML
    Pane filterBox, pane;

    @FXML
    Label sub;

    @FXML
    JFXHamburger hamburger;

    private ObservableList<BusinessHistoryItemVO> observableList = FXCollections.observableArrayList();

    private DocBLService blService = DocBLServiceFactory.getDocBLService();

    private DocumentFilter.Builder builder = new DocumentFilter.Builder();

    private HamburgerBasicCloseTransition burgerTask = new HamburgerBasicCloseTransition();

    private JFXNodesList nodesList = new JFXNodesList();

    private DocumentFilter.Builder getBuilder() {
        return builder;
    }

    private Upper upper=this;

    void refresh() {
        observableList.setAll(blService.findBusinessHistory(getBuilder().build()));
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        DateHelper.setDefaultTime(startDate, DateHelper.weekAgo());
        DateHelper.setDefaultTime(endDate, DateHelper.dateToLocalDate(new Date()));

        tableView.setItems(observableList);

        refresh();

        type.setCellValueFactory(c ->
                new SimpleStringProperty(DocHelper.typeToString(c.getValue().getType())));

        customer.setCellValueFactory(c ->
                new SimpleStringProperty(c.getValue().getCustomer()));

        creator.setCellValueFactory(c ->
                new SimpleStringProperty(c.getValue().getSalesman()));

        time.setCellValueFactory(c ->
                new SimpleStringProperty(DateHelper.accurateTime(c.getValue().getDate())));

        repository.setCellValueFactory(c ->
                new SimpleStringProperty(c.getValue().getRepository()));

        btn.setCellFactory(p -> {
            return new TableCell() {
                @Override
                protected void updateItem(Object item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        JFXButton openBtn = new JFXButton("查看");
                        openBtn.setOnAction(e -> {
                            DocVO docVO = ((BusinessHistoryItemVO) getTableView().getItems().get(getIndex()))
                                    .getDocVO();
                            FXMLLoader loader=new FXMLLoader(getClass().getResource("BusinessHistoryDetail.fxml"));

                            try {
                                setChildren(loader.load(), ">单据详情");
                                ((BusinessHistoryDetail)loader.getController()).init(docVO,upper);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        });

                        TableViewHelper.setNodeVisibleOnlyMouseEntered(getTableRow(), openBtn);
                        setGraphic(openBtn);
                        setText(null);
                    }
                }
            };
        });

        DocHelper.addFilter(burgerTask, hamburger, nodesList, filterBox, pane);


    }

    @Override
    public void back() {
        setChildren(mainPane, "");
        refresh();
    }

    @Override
    public void setChildren(Node node, String title) {
        container.getChildren().setAll(node);
        sub.setText(title);
    }
}
