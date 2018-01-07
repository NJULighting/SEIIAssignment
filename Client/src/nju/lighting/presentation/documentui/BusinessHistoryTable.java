package nju.lighting.presentation.documentui;

import com.jfoenix.controls.*;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import nju.lighting.blservice.documentblservice.DocBLService;
import nju.lighting.presentation.DialogUI.DialogHelper;
import nju.lighting.presentation.factory.DocBLServiceFactory;
import nju.lighting.presentation.factory.UserBLServiceFactory;
import nju.lighting.presentation.mainui.Upper;
import nju.lighting.presentation.utils.*;
import nju.lighting.vo.CustomerVO;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.UserVO;
import nju.lighting.vo.viewtables.BusinessHistoryItemVO;
import shared.DocType;
import shared.DocumentFilter;
import shared.ResultMessage;

import java.io.File;
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
    TableColumn<BusinessHistoryItemVO, String> type, customer, creator, time, salesman;

    @FXML
    TableColumn btn;

    @FXML
    TableView<BusinessHistoryItemVO> tableView;

    @FXML
    JFXDatePicker startDate, endDate;

    @FXML
    JFXComboBox<DocType> typeBox;

    @FXML
    JFXComboBox repositoryBox;

    @FXML
    JFXComboBox<UserVO> creatorBox;

    @FXML
    JFXTextField customerText;

    @FXML
    HBox container, labelBox;

    @FXML
    AnchorPane mainPane;

    @FXML
    Pane filterBox, pane;

    @FXML
    StackPane stackPane;

    @FXML
    Label sub;

    @FXML
    Button setCustomerBtn;

    @FXML
    JFXButton okBtn, export;

    @FXML
    JFXHamburger hamburger;

    private ObservableList<BusinessHistoryItemVO> observableList = FXCollections.observableArrayList();

    private DocBLService blService = DocBLServiceFactory.getDocBLService();

    private DocumentFilter.Builder builder;

    private SimpleObjectProperty<CustomerVO> customerProperty = new SimpleObjectProperty<>();

    private HamburgerBasicCloseTransition burgerTask = new HamburgerBasicCloseTransition();

    private JFXNodesList nodesList = new JFXNodesList();


    private DocumentFilter.Builder getBuilder() {
        builder = new DocumentFilter.Builder();
        Date end = DateHelper.localDateToDate(endDate.getValue());
        end.setDate(end.getDate() + 1);
        builder.startDate(DateHelper.localDateToDate(startDate.getValue()))
                .endDate(end)
                .docType(typeBox.getValue());
        if (creatorBox.getValue() != null)
            builder.creatorID(creatorBox.getValue().getID());
        else builder.creatorID(null);
        if (customerProperty.getValue() != null)
            builder.customer(customerProperty.getValue().getID() + "");
        else builder.customer(null);

        return builder;
    }

    private Upper upper = this;

    private ObservableList<Node> list = FXCollections.observableArrayList();

    void refresh() {
        observableList.setAll(blService.findBusinessHistory(getBuilder().build()));
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        okBtn.setOnAction(e -> {
            refresh();
            DocHelper.search(nodesList, burgerTask);
            container.getChildren().setAll(mainPane);
            labelBox.getChildren().clear();
            list.clear();
        });

        DateHelper.setDefaultTime(startDate, DateHelper.weekAgo());
        DateHelper.setDefaultTime(endDate, DateHelper.dateToLocalDate(new Date()));

        creatorBox.getItems().add(new UserVO("无", null, null, false));
        creatorBox.getItems().addAll(UserBLServiceFactory.getUserBLService().getUserList());

        typeBox.getItems().addAll(DocType.values());
        typeBox.setValue(typeBox.getItems().get(0));

        setCustomerBtn.setOnAction(e -> CustomerHelper.setCustomer(this, customerProperty));

        customerProperty.addListener(c -> customerText.setText(customerProperty.getValue().getName()));


        tableView.setItems(observableList);

        refresh();

        type.setCellValueFactory(c ->
                new SimpleStringProperty(c.getValue().getType().toString()));

        customer.setCellValueFactory(c ->
                new SimpleStringProperty(c.getValue().getCustomer()));

        creator.setCellValueFactory(c ->
                new SimpleStringProperty(c.getValue().getSalesman()));

        time.setCellValueFactory(c ->
                new SimpleStringProperty(DateHelper.accurateTime(c.getValue().getDate())));

        salesman.setCellValueFactory(c ->
                new SimpleStringProperty(UserHelper.getUserString(c.getValue().getSalesman())));

        TextFieldHelper.addDeleteMenuItem(customerText,customerProperty);


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
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("BusinessHistoryDetail.fxml"));

                            try {
                                setChildren(loader.load(), ">单据详情");
                                ((BusinessHistoryDetail) loader.getController()).init(docVO, upper);
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

    @FXML
    void backToMain() {
        list.clear();
        labelBox.getChildren().clear();
        container.getChildren().setAll(mainPane);
        nodesList.setVisible(true);
    }

    @Override
    public void back() {
        if (labelBox.getChildren().size() == 1)
            backToMain();
        else {
            labelBox.getChildren().remove(labelBox.getChildren().get(labelBox.getChildren().size() - 1));
            container.getChildren().setAll(list.get(list.size() - 2));
            list.remove(list.size() - 1);
        }
    }

    @Override
    public void setChildren(Node node, String title) {
        if (node.equals(mainPane))
            nodesList.setVisible(true);
        else nodesList.setVisible(false);
        list.add(node);
        container.getChildren().setAll(node);
        Label label = new Label(title);
        label.setFont(Font.font(20));
        label.setOnMouseClicked(e -> {
            container.getChildren().setAll(node);
            labelBox.getChildren().remove(labelBox.getChildren().indexOf(label) + 1,
                    labelBox.getChildren().size());
            list.remove(list.indexOf(node) + 1, list.size());
        });

        labelBox.getChildren().add(label);
    }

    public void exportExcel() {

        if(ExportExcelHelper.businessHistoryTable(observableList)==ResultMessage.SUCCESS){
            DialogHelper.dialog("导出表格", ResultMessage.SUCCESS, stackPane);
        } else {
            DialogHelper.dialog("导出表格", ResultMessage.FAILURE, stackPane);
        }
    }
}
