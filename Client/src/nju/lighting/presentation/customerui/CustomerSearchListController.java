package nju.lighting.presentation.customerui;


import com.jfoenix.controls.JFXButton;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import nju.lighting.bl.customerbl.Customer;
import nju.lighting.bl.customerbl.CustomerBLService_Stub;
import nju.lighting.blservice.customerblservice.CustomerBLService;
import nju.lighting.presentation.mainui.CustomerUpper;
import nju.lighting.presentation.mainui.Upper;
import nju.lighting.presentation.utils.CustomerHelper;
import nju.lighting.presentation.utils.TableViewHelper;
import nju.lighting.vo.CustomerVO;
import shared.ResultMessage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class CustomerSearchListController implements Initializable {

    private CustomerBLService customerBLService = new CustomerBLService_Stub();
    private List<CustomerVO> customerVOList;
    CustomerSearchListController customerSearchListController = this;
    Upper upper;
    Label title;

    private CustomerVO selectedCustomer;

    @FXML
    private TableView<CustomerVO> tableView;


    @FXML
    private Pane pane;


    @FXML
    private TextField search;

    @FXML
    private Button searchBtn;

    @FXML
    private Button deleteSearch;

    @FXML
    private Button addCustomerBtn;

    @FXML
    private Pagination customerList;

    @FXML
    private Label noResult;

    @FXML
    TableColumn<CustomerVO, Integer> customerId;

    @FXML
    TableColumn<CustomerVO, String> type;

    @FXML
    TableColumn<CustomerVO, String> name;

    @FXML
    TableColumn<CustomerVO, String> grade;


    @FXML
    TableColumn<CustomerVO, Double> receive;

    @FXML
    TableColumn<CustomerVO, Double> pay;

    @FXML
    TableColumn<CustomerVO, Double> receiveLimit;

    @FXML
    TableColumn<CustomerVO, String> telephone;
    @FXML
    TableColumn<CustomerVO, String> salesman;

    @FXML
    TableColumn<CustomerVO, String> openBtn;

    HBox father;


    //清除搜索，列表显示所有客户
    public void setDeleteSearch() {
        search.setText("");
        customerVOList = customerBLService.getCustomerList();
        // setCustomerList();

        noResult.setVisible(false);

        deleteSearch.setDisable(true);
        deleteSearch.setVisible(false);
    }

    //关键字字数不超过8个字
    public void keyTyped(Event e) {
        String s = search.getText();
        if (s.length() >= 8) e.consume();
    }

    public void search() {
        String keywords = search.getText();
        if (keywords == null || keywords.length() == 0) {//无关键词，则显示所有列表
            customerVOList = customerBLService.getCustomerList();
            // setCustomerList();
        } else {//有关键词，删除搜索键可见
            deleteSearch.setDisable(false);
            deleteSearch.setVisible(true);

            List<CustomerVO> customerVOS = customerBLService.search(keywords);
            if (customerVOS != null) {
                noResult.setVisible(false);
                customerVOList = customerVOS;
                // setCustomerList();
            } else {
                noResult.setVisible(true);
            }
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        customerVOList = customerBLService.getCustomerList();

        customerId.setCellValueFactory(p ->
                new SimpleIntegerProperty(p.getValue().getID()).asObject());

        type.setCellValueFactory(p ->
                new SimpleStringProperty(CustomerHelper.typeToString(p.getValue().getType())));

        name.setCellValueFactory(p ->
                new SimpleStringProperty(p.getValue().getName()));

        grade.setCellValueFactory(p ->
                new SimpleStringProperty(CustomerHelper.gradeToString(p.getValue().getGrade())));

        receive.setCellValueFactory(p ->
                new SimpleDoubleProperty(p.getValue().getReceivable()).asObject());

        pay.setCellValueFactory(p ->
                new SimpleDoubleProperty(p.getValue().getPayable()).asObject());

        receiveLimit.setCellValueFactory(p ->
                new SimpleDoubleProperty(p.getValue().getReceivableLimit()).asObject());

        telephone.setCellValueFactory(p ->
                new SimpleStringProperty(p.getValue().getTelephone()));

        salesman.setCellValueFactory(p ->
                new SimpleStringProperty(p.getValue().getSalesman()));

        openBtn.setCellValueFactory(p ->
                new SimpleStringProperty(p.getValue().getSalesman()));

        openBtn.setCellFactory(new Callback<TableColumn<CustomerVO, String>, TableCell<CustomerVO, String>>() {
            @Override
            public TableCell<CustomerVO, String> call(TableColumn<CustomerVO, String> param) {
                return new TableCell<CustomerVO, String>() {
                    JFXButton openBtn = new JFXButton("查看");
                    JFXButton deleteBtn = new JFXButton("删除");
                    HBox buttonBox = new HBox(openBtn, deleteBtn);


                    @Override
                    protected void updateItem(String item, boolean empty) {
                        TableRow tableRow = getTableRow();
                        tableRow.setOnMouseEntered(e -> {
                            buttonBox.setVisible(true);
                        });

                        tableRow.setOnMouseExited(e -> {
                            buttonBox.setVisible(false);
                        });
                        super.updateItem(item, empty);
                        if (empty) {
                            setText(null);
                            setGraphic(null);
                        } else {
                            CustomerVO customerVO = getTableView().getItems().get(getTableRow().getIndex());
                            setText(null);
                            setGraphic(buttonBox);
                            buttonBox.setVisible(false);
                            buttonBox.setSpacing(50);
                            openBtn.setOnAction(e -> {
                                customerDetail(false,customerVO);
                            });

                            deleteBtn.setOnAction(e -> {
                                ResultMessage resultMessage = customerBLService.deleteCustomer(customerVO.getID());
                                getTableView().getItems().remove(customerVO);
                            });
                        }
                    }
                };
            }
        });

        ObservableList<CustomerVO> observableList = FXCollections.observableArrayList();

        observableList.addAll(customerVOList.stream()
                .collect(Collectors.toList()));

        tableView.setItems(observableList);
        TableViewHelper.commonSet(tableView);

        addCustomerBtn.setOnAction(e -> {
            customerDetail(true,null);
        });

    }

    void customerDetail(boolean add,CustomerVO customerVO){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerDetail.fxml"));
        father.getChildren().clear();
        try {
            father.getChildren().add(loader.load());
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        CustomerDetail controller = loader.getController();
        if (add)
            title.setText(">增加客户");
        else{
            title.setText(">客户详情");
        }

        controller.setController(customerSearchListController);
        controller.setCustomerVO(customerVO);
        controller.setUpper(upper);
        controller.init(add);

    }

    public void setReadOnly(CustomerUpper upper) {
        tableView.getColumns().remove(openBtn);
        addCustomerBtn.setText("确定");
        addCustomerBtn.setOnAction(e->{
            if (!tableView.getSelectionModel().isEmpty()){
                selectedCustomer=tableView.getItems().get(tableView.getSelectionModel().getSelectedIndex());
                upper.setCustomer(selectedCustomer);
                upper.back();

            }


        });
    }

    public CustomerBLService getCustomerBLService() {
        return customerBLService;
    }

    public void setUpper(Upper upper) {
        this.upper = upper;
    }

    public Pane getPane() {
        return pane;
    }

    public TableView<CustomerVO> getTableView() {
        return tableView;
    }
}
