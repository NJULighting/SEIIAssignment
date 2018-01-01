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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import nju.lighting.blservice.documentblservice.DocBLService;
import nju.lighting.presentation.factory.DocBLServiceFactory;
import nju.lighting.presentation.factory.UserBLServiceFactory;
import nju.lighting.presentation.mainui.Upper;
import nju.lighting.presentation.utils.*;
import nju.lighting.vo.CustomerVO;
import nju.lighting.vo.UserVO;
import nju.lighting.vo.commodity.BasicCommodityItemVO;
import nju.lighting.vo.viewtables.SalesDetailItemVO;
import shared.DocumentFilter;
import shared.Identity;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created on 2017/12/9.
 * Description
 *
 * @author 陈俊宇
 */
public class SalesDetailTable implements Initializable, Upper {
    //    private JFXNodesList nodeList;
//
    private HamburgerBasicCloseTransition burgerTask;
    private JFXNodesList nodesList = new JFXNodesList();

    private SimpleObjectProperty<CustomerVO> customerProperty = new SimpleObjectProperty<>();
    private SimpleObjectProperty<UserVO> creatorProperty = new SimpleObjectProperty<>();
    private SimpleObjectProperty<BasicCommodityItemVO> commodityProperty = new SimpleObjectProperty<>();
    private DocumentFilter.Builder builder = new DocumentFilter.Builder();
    private DocBLService blService = DocBLServiceFactory.getDocBLService();

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
    JFXComboBox creatorBox;

    @FXML
    Pane filterBox, pane;

    @FXML
    JFXDatePicker startDate, endDate;

    @FXML
    JFXButton okBtn, resetBtn;


    ObservableList<SalesDetailItemVO> observableList = FXCollections.observableArrayList();

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


    public JFXNodesList getNodesList() {
        return nodesList;
    }


    HamburgerBasicCloseTransition getBurgTask() {
        return burgerTask;
    }

    void refresh() {
        observableList.setAll(blService.findSaleRecords(getBuilder().build()));
        back();
        nodesList.animateList(false);
        burgerTask.setRate(burgerTask.getRate() * -1);

        burgerTask.play();
    }

    private DocumentFilter.Builder getBuilder() {
        DocumentFilter.Builder builder = new DocumentFilter.Builder();
        builder.startDate(DateHelper.localDateToDate(startDate.getValue()))
                .endDate(DateHelper.localDateToDate(endDate.getValue()))
                .commodity(commodityText.getText());
        if (customerProperty.getValue() != null)
            builder.customer(customerProperty.getValue().getID() + "");
        if (creatorProperty.getValue() != null)
            builder.creatorID(creatorProperty.getValue().getID());

        return builder;

    }


    public void initialize(URL location, ResourceBundle resources) {

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
        TableViewHelper.setHeight(tableView,1.1);
        tableView.requestFocus();

        DateHelper.setDefaultTime(startDate, DateHelper.weekAgo());
        DateHelper.setDefaultTime(endDate, DateHelper.dateToLocalDate(new Date()));
        observableList.setAll(blService.findSaleRecords(getBuilder().build()));

        setCustomerBtn.setOnAction(e -> CustomerHelper.setCustomer(this, customerProperty));
        setCommodityBtn.setOnAction(e -> CommodityHelper.setCommodity(this, commodityProperty));

        customerProperty.addListener(c ->{
            if (customerProperty.getValue()!=null)
                customerText.setText(customerProperty.getValue().getName());
        });
        commodityProperty.addListener(c -> commodityText.setText(commodityProperty.getValue().getName()));

        customerText.textProperty().addListener(c-> {
            if (customerText.getText().length()==0)
                customerProperty.set(null);
        });

        creatorBox.getItems().addAll(UserBLServiceFactory.getUserBLService().getUserList(Identity.SYSTEM_ADMIN));

        burgerTask = new HamburgerBasicCloseTransition(hamburger);

        burgerTask.setRate(-1);

        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {

            burgerTask.setRate(burgerTask.getRate() * -1);

            burgerTask.play();

            System.out.println(nodesList.isExpanded());
        });

        nodesList.setSpacing(30);

        nodesList.getChildren().clear();
        nodesList.addAnimatedNode(hamburger);
        nodesList.addAnimatedNode(filterBox);

        nodesList.setMargin(hamburger, new Insets(0, 0, 0, 990));
        nodesList.setMargin(filterBox, new Insets(-20, 0, 0, 20));
        nodesList.setPadding(new Insets(25, 0, 0, 40));
        pane.getChildren().add(nodesList);


        okBtn.setOnAction(e -> {
            refresh();
        });

    }


}
