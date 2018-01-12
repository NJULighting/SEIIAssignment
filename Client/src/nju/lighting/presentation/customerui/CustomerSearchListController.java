package nju.lighting.presentation.customerui;


import com.jfoenix.controls.JFXButton;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import nju.lighting.blservice.customerblservice.CustomerBLService;
import nju.lighting.presentation.DialogUI.DialogHelper;
import nju.lighting.presentation.factory.CustomerBLServiceFactory;
import nju.lighting.presentation.mainui.MainUI;
import nju.lighting.presentation.mainui.Upper;
import nju.lighting.presentation.utils.CustomerHelper;
import nju.lighting.presentation.utils.TableViewHelper;
import nju.lighting.presentation.utils.UserHelper;
import nju.lighting.vo.CustomerVO;
import shared.CustomerType;
import shared.ResultMessage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class CustomerSearchListController implements Initializable {

    Label title;
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

    private ObservableList<CustomerVO> observableList = FXCollections.observableArrayList();
    private StringProperty keyWord = new SimpleStringProperty();
    HBox father;
    private CustomerBLService customerBLService = CustomerBLServiceFactory.getCustomerBLService();
    private CustomerSearchListController customerSearchListController = this;
    private Upper upper;
    private CustomerVO selectedCustomer;

    private CustomerType customerType = CustomerType.ALL;


    //清除搜索，列表显示所有客户
    public void setDeleteSearch() {
        search.setText("");
        deleteSearch.setDisable(true);
        deleteSearch.setVisible(false);
        refresh();
    }

    //关键字字数不超过8个字
    public void keyTyped(Event e) {
        String s = search.getText();
        if (s.length() >= 8) e.consume();
    }

    public void search() {
        String keywords = search.getText();
        if (keywords == null || keywords.length() == 0) {//无关键词，则显示所有列表
            refresh();
        } else {//有关键词，删除搜索键可见
            deleteSearch.setDisable(false);
            deleteSearch.setVisible(true);

            List list = customerBLService.search(keywords, customerType);
            System.out.println(list);
            if (list == null)
                observableList.clear();
            else
                observableList.setAll(list);
        }
    }

    void refresh() {
        List<CustomerVO> customerVOList = customerBLService.findCustomerByType(customerType);
        if (customerVOList!=null&&customerVOList.size() != 0)
            observableList.setAll(customerVOList.stream()
                    .collect(Collectors.toList()));
        else
            observableList.clear();
    }

    void delete(CustomerVO customerVO) {
        //如果存在应收应付，则不可删除
        if(customerVO.getPayable()!=0||customerVO.getReceivable()!=0){
            Label cannot = new Label("客户尚有应收应付，不可删除");
            DialogHelper.addDialog(cannot,MainUI.getStackPane());

        }else {
            DialogHelper.addDialog("你确定要删除用户" + customerVO.getName() + "?",
                    MainUI.getStackPane(),
                    new EventHandler() {
                        @Override
                        public void handle(Event event) {
                            ResultMessage resultMessage = customerBLService.deleteCustomer(customerVO.getID());
                            if (resultMessage == ResultMessage.SUCCESS) {
                                upper.back();
                                getTableView().getItems().remove(customerVO);
                            }
                            DialogHelper.dialog("删除用户", resultMessage, MainUI.getStackPane());
                        }
                    }
            );
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        keyWord.bind(search.textProperty());
        search.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.length() == 0) {
                    refresh();
                    setDeleteSearch();
                }
            }
        });

        search.setOnAction(e -> search());

        refresh();

        customerId.setCellValueFactory(p ->
                new SimpleIntegerProperty(p.getValue().getID()).asObject());


        type.setCellValueFactory(p ->
                new SimpleStringProperty(p.getValue().getType().toString()));
        type.setCellFactory(p -> new HighLightCell(keyWord));

        name.setCellValueFactory(p ->
                new SimpleStringProperty(p.getValue().getName()));
        name.setCellFactory(p -> new HighLightCell(keyWord));

        grade.setCellValueFactory(p ->
                new SimpleStringProperty(p.getValue().getGrade().toString()));
        grade.setCellFactory(p -> new HighLightCell(keyWord));

        receive.setCellValueFactory(p ->
                new SimpleDoubleProperty(p.getValue().getReceivable()).asObject());
        receive.setCellFactory(p -> new HighLightCell(keyWord));

        pay.setCellValueFactory(p ->
                new SimpleDoubleProperty(p.getValue().getPayable()).asObject());
        pay.setCellFactory(p -> new HighLightCell(keyWord));

        receiveLimit.setCellValueFactory(p ->
                new SimpleDoubleProperty(p.getValue().getReceivableLimit()).asObject());
        receiveLimit.setCellFactory(p -> new HighLightCell(keyWord));

        telephone.setCellValueFactory(p ->
                new SimpleStringProperty(p.getValue().getTelephone()));
        telephone.setCellFactory(p -> new HighLightCell(keyWord));

        salesman.setCellValueFactory(p ->
                new SimpleStringProperty(UserHelper.getUserString(p.getValue().getSalesman())));
        salesman.setCellFactory(p -> new HighLightCell(keyWord));

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
                                customerDetail(false, customerVO);
                            });

                            deleteBtn.setOnAction(e -> {
                                delete(customerVO);
                            });
                        }
                    }
                };
            }
        });


        tableView.setItems(observableList);
        TableViewHelper.commonSet(tableView);

        addCustomerBtn.setOnAction(e -> {
            customerDetail(true, null);
        });

    }

    void customerDetail(boolean add, CustomerVO customerVO) {
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
        else {
            title.setText(">客户详情");
        }

        controller.setController(customerSearchListController);
        controller.setCustomerVO(customerVO);
        controller.setUpper(upper);
        controller.init(add);

    }

    public void setReadOnly(Upper upper, SimpleObjectProperty<CustomerVO> customer) {
        tableView.getColumns().remove(openBtn);
        addCustomerBtn.setText("确定");
        addCustomerBtn.setOnAction(e -> {
            if (!tableView.getSelectionModel().isEmpty()) {
                selectedCustomer = tableView.getItems().get(tableView.getSelectionModel().getSelectedIndex());
                customer.set(selectedCustomer);
                upper.back();

            }


        });

        refresh();
    }

    public void setReadOnly(Upper upper, SimpleObjectProperty<CustomerVO> customer, CustomerType type) {
        setReadOnly(upper, customer);
        customerType = type;

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
