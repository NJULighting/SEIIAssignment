package nju.lighting.presentation.documentui;

import com.jfoenix.controls.*;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import nju.lighting.blservice.documentblservice.DocBLService;
import nju.lighting.presentation.DialogUI.DialogHelper;
import nju.lighting.presentation.factory.DocBLServiceFactory;
import nju.lighting.presentation.factory.UserBLServiceFactory;
import nju.lighting.presentation.mainui.Upper;
import nju.lighting.presentation.utils.*;
import nju.lighting.vo.CustomerVO;
import nju.lighting.vo.UserVO;
import nju.lighting.vo.commodity.BasicCommodityItemVO;
import nju.lighting.vo.viewtables.SalesDetailItemVO;
import shared.DocumentFilter;
import shared.ResultMessage;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Created on 2017/12/9.
 * Description 销售明细表的控制类
 *
 * @author 陈俊宇
 */
public class SalesDetailTable implements Initializable, Upper {
    @FXML
    StackPane stackPane;
    @FXML
    AnchorPane mainPane;
    @FXML
    HBox container;
    @FXML
    Label sub;
    @FXML
    JFXHamburger hamburger;
    @FXML
    JFXTextField customerText, commodityText;
    @FXML
    Button setCommodityBtn, setCustomerBtn;
    @FXML
    JFXComboBox<UserVO> creatorBox;
    @FXML
    JFXComboBox<String> repositoryBox;
    @FXML
    Pane filterBox, pane;
    @FXML
    JFXDatePicker startDate, endDate;
    @FXML
    JFXButton okBtn, resetBtn;

    @FXML
    TableColumn<SalesDetailItemVO, String> commodity, modelNumber, time;
    @FXML
    TableColumn<SalesDetailItemVO, Double> price, total;
    @FXML
    TableColumn<SalesDetailItemVO, Integer> count;
    @FXML
    TableColumn openBtn;
    @FXML
    TableView<SalesDetailItemVO> tableView;

    private ObservableList<SalesDetailItemVO> observableList = FXCollections.observableArrayList();
    private HamburgerBasicCloseTransition burgerTask = new HamburgerBasicCloseTransition();
    private JFXNodesList nodesList = new JFXNodesList();
    private SimpleObjectProperty<CustomerVO> customerProperty = new SimpleObjectProperty<>();
    private SimpleObjectProperty<UserVO> creatorProperty = new SimpleObjectProperty<>();
    private SimpleObjectProperty<BasicCommodityItemVO> commodityProperty = new SimpleObjectProperty<>();
    private DocumentFilter.Builder builder = new DocumentFilter.Builder();
    private DocBLService blService = DocBLServiceFactory.getDocBLService();

    @Override

    public void back() {
        setChildren(mainPane, "");
    }

    @Override
    public void setChildren(Node node, String title) {
        if (node.equals(mainPane))
            nodesList.setVisible(true);
        else nodesList.setVisible(false);
        container.getChildren().setAll(node);
        sub.setText(title);
    }

    @FXML
    private void clear(){
        customerProperty.set(null);
        creatorBox.setValue(null);
        commodityProperty.set(null);
    }


    public JFXNodesList getNodesList() {
        return nodesList;
    }


    HamburgerBasicCloseTransition getBurgTask() {
        return burgerTask;
    }

    private void refresh() {

        observableList.setAll(blService.findSaleRecords(getBuilder().build()));
    }



    private DocumentFilter.Builder getBuilder() {
        Date end = DateHelper.localDateToDate(endDate.getValue());
        end.setDate(end.getDate() + 1);

        DocumentFilter.Builder builder = new DocumentFilter.Builder();
        builder.startDate(DateHelper.localDateToDate(startDate.getValue()))
                .endDate(end)
                .commodity(commodityText.getText());
        if (commodityText.getText().length() == 0)
            builder.commodity(null);
        if (customerProperty.getValue() != null)
            builder.customer(customerProperty.getValue().getID() + "");
        if (creatorProperty.getValue() != null)
            builder.creatorID(creatorProperty.getValue().getID());

        return builder;

    }


    public void initialize(URL location, ResourceBundle resources) {

        TextFieldHelper.addDeleteMenuItem(customerText, customerProperty);
        TextFieldHelper.addDeleteMenuItem(commodityText, commodityProperty);

        commodity.setCellValueFactory(c ->
                new SimpleStringProperty(c.getValue().getName()));
        modelNumber.setCellValueFactory(c ->
                new SimpleStringProperty(c.getValue().getCommodityType()));
        time.setCellValueFactory(c ->
                new SimpleStringProperty(DateHelper.approximateTime(c.getValue().getDate())));
        price.setCellValueFactory(c ->
                new SimpleDoubleProperty(c.getValue().getSalePrice()).asObject());
        total.setCellValueFactory(c ->
                new SimpleDoubleProperty(c.getValue().getTotalAmount()).asObject());
        count.setCellValueFactory(c ->
                new SimpleIntegerProperty(
                        (int) (c.getValue().getTotalAmount() / c.getValue().getSalePrice())).asObject());

        tableView.setItems(observableList);
        TableViewHelper.commonSet(tableView);
        TableViewHelper.setHeight(tableView, 1.1);
        tableView.requestFocus();

        DateHelper.setDefaultTime(startDate, DateHelper.weekAgo());
        DateHelper.setDefaultTime(endDate, DateHelper.dateToLocalDate(new Date()));
        observableList.setAll(blService.findSaleRecords(getBuilder().build()));

        setCustomerBtn.setOnAction(e -> CustomerHelper.setCustomer(this, customerProperty));
        setCommodityBtn.setOnAction(e -> CommodityHelper.setCommodity(this, commodityProperty));

        customerProperty.addListener(c -> {
            if (customerProperty.getValue() != null)
                customerText.setText(customerProperty.getValue().getName());
            else
                customerText.setText(null);
        });
        commodityProperty.addListener(c -> commodityText.setText(commodityProperty.getValue().getName()));

        customerText.textProperty().addListener(c -> {
            if (customerText.getText().length() == 0)
                customerProperty.set(null);
        });

        creatorBox.getItems().add(new UserVO("无", null, null, false));
        creatorBox.getItems().addAll(UserBLServiceFactory.getUserBLService().getUserList());
        creatorBox.setValue(creatorBox.getItems().get(0));


        repositoryBox.setValue("默认仓库");


        DocHelper.addFilter(burgerTask, hamburger, nodesList, filterBox, pane);

        okBtn.setOnAction(e -> {
            refresh();
            DocHelper.search(nodesList, burgerTask);
            back();
        });

    }


    public void exportExcel() {
        if (ExportExcelHelper.salesDetailTable(observableList) == ResultMessage.SUCCESS) {
            DialogHelper.dialog("导出表格", ResultMessage.SUCCESS, stackPane);
        } else {
            DialogHelper.dialog("导出表格", ResultMessage.FAILURE, stackPane);
        }
    }
}
