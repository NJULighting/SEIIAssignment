package nju.lighting.presentation.customerui;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import nju.lighting.bl.customerbl.CustomerBLService_Stub;
import nju.lighting.blservice.customerblservice.CustomerBLService;
import nju.lighting.presentation.mainui.Client;
import nju.lighting.vo.CustomerVO;

import shared.*;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerSearchListController implements Initializable {

    private CustomerBLService customerBLService = new CustomerBLService_Stub();
    private List<CustomerVO> customerVOList;
    private int itemsPerPage = 9;

    @FXML
    private TableView<CustomerVO> tableView;

    @FXML
    StackPane stackPane;

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
    TableColumn<CustomerVO,Integer> customerId;

    @FXML
    TableColumn<CustomerVO,String> type;

    @FXML
    TableColumn<CustomerVO,String> name;

    @FXML
    TableColumn<CustomerVO,String> grade;


    @FXML
    TableColumn<CustomerVO,Double> receive;

    @FXML
    TableColumn<CustomerVO,Double> pay;

    @FXML
    TableColumn<CustomerVO,Double> receiveLimit;

    @FXML
    TableColumn<CustomerVO,String> telephone;
    @FXML
    TableColumn<CustomerVO,String> salesman;


    //=======secondPane=======//


    //=========secondPane===========//


//    //清除搜索，列表显示所有客户
//    public void setDeleteSearch(){
//        search.setText("");
//        customerVOList = customerBLService.getCustomerList();
//        setCustomerList();
//
//        noResult.setVisible(false);
//
//        deleteSearch.setDisable(true);
//        deleteSearch.setVisible(false);
//    }
//
//    //关键字字数不超过8个字
//    public void keyTyped(Event e){
//        String s = search.getText();
//        if(s.length() >= 8) e.consume();
//    }
//
//    public void search(){
//        String keywords = search.getText();
//        if(keywords==null||keywords.length()==0){//无关键词，则显示所有列表
//            customerVOList = customerBLService.getCustomerList();
//            setCustomerList();
//        }
//        else{//有关键词，删除搜索键可见
//            deleteSearch.setDisable(false);
//            deleteSearch.setVisible(true);
//
//            List<CustomerVO> customerVOS = customerBLService.search(keywords);
//            if(customerVOS!=null){
//                noResult.setVisible(false);
//                customerVOList = customerVOS;
//                setCustomerList();
//            }else{
//                noResult.setVisible(true);
//            }
//        }
//    }

    //设置客户列表的显示
//    private void setCustomerList(){
//        customerList.setPageCount((customerVOList.size() / itemsPerPage) + 1);
//        customerList.setPageFactory((Integer index) -> createPage(index));
//    }
//
//    ListView createPage(int index) {
//        ListView<CustomerVO> customerVOJFXListView = new JFXListView<>();
//        ObservableList<CustomerVO> information = FXCollections.observableArrayList();
//
//        int left = customerVOList.size() - index * itemsPerPage;
//        int max = (left > itemsPerPage) ? itemsPerPage : left;
//
//        for (int i = 0; i < max; i++) {
//            information.add(customerVOList.get(index * itemsPerPage + i));
//        }
//
//        customerVOJFXListView.setItems(information);
//        customerVOJFXListView.setCellFactory((ListView<CustomerVO> l) -> new myListCell());
//        System.out.println(customerVOJFXListView.getStylesheets());
//        System.out.println(customerVOJFXListView.getStylesheets());
//
//        return customerVOJFXListView;
//    }
//
//
//    class myListCell extends ListCell<CustomerVO> {
//        Label id,type,name,grade,receive,pay,receiveLimit,telephone,salesman;
//        HBox buttonBox;
//        JFXButton openBtn,invalidBtn;
//        HBox box;
//        Pane customerMessage;
//
//        @Override
//        public void updateItem(CustomerVO item, boolean empty) {
//            super.updateItem(item,empty);
//            if (item != null) {
//                id = new Label(String.format("%06d",item.getID()));
//                id.setPrefWidth(60);
//
//                if(item.getType()== CustomerType.SALESPERSON)
//                    type = new Label("销售商");
//                else
//                    type = new Label("供应商");
//                type.setPrefWidth(45);
//
//                name = new Label(item.getName());
//                name.setPrefWidth(60);
//
//                switch (item.getGrade()){
//                    case ONE:
//                        grade = new Label("一级");
//                        break;
//                    case TWO:
//                        grade = new Label("二级");
//                        break;
//                    case THREE:
//                        grade = new Label("三级");
//                        break;
//                    case FOUR:
//                        grade = new Label("四级");
//                        break;
//                    case FIVE:
//                        grade = new Label("五级(vip)");
//                        break;
//                    default:
//                        grade = new Label(" ");
//                        break;
//                }
//                grade.setPrefWidth(82);
//
//                receive = new Label(String.valueOf(item.getReceivable()));
//                receive.setPrefWidth(74);
//                pay = new Label(String.valueOf(item.getPayable()));
//                pay.setPrefWidth(74);
//                receiveLimit = new Label(String.valueOf(item.getReceivableLimit()));
//                receiveLimit.setPrefWidth(74);
//                telephone = new Label(item.getTelephone());
//                telephone.setPrefWidth(97);
//                salesman = new Label(item.getSalesman());
//                salesman.setPrefWidth(75);
//
//                openBtn = new JFXButton("查看");
//                openBtn.setTextFill(Color.valueOf("#5599ff"));
//                invalidBtn = new JFXButton("删除");
//                invalidBtn.setTextFill(Color.valueOf("#5599ff"));
//                buttonBox = new HBox(openBtn,invalidBtn);
//                buttonBox.setAlignment(Pos.CENTER_RIGHT);
//                buttonBox.setSpacing(20);
//                buttonBox.setPadding(new Insets(0,0,0,15));
//                buttonBox.setVisible(false);
//
//
//
//                //查看客户
//                openBtn.setOnAction(new EventHandler<ActionEvent>() {
//                    @Override
//                    public void handle(ActionEvent event) {
//
//                        try {
//                            FXMLLoader loader=new FXMLLoader(getClass().getResource("CustomerDetail.fxml"));
//                            stackPane.getChildren().clear();
//                            stackPane.getChildren().add(loader.load());
//                            CustomerDetail controller=loader.getController();
//                            controller.setCustomerVO(item);
//                            controller.setCustomerBLService(customerBLService);
//                            controller.setBackPane(pane);
//                            controller.setFather(stackPane);
//                            controller.inti();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                });
//
//                invalidBtn.setOnAction(new EventHandler<ActionEvent>() {
//                    @Override
//                    public void handle(ActionEvent event) {
//                        CustomerBLService customerBLService = new CustomerBLService_Stub();
//                        ResultMessage resultMessage = customerBLService.deleteCustomer(Integer.parseInt(id.getText()));
//                        System.out.println("delete customer: "+resultMessage);
//                        //CustomerManageController
//                    }
//                });
//
//                box = new HBox(id,type,name,grade,receive,pay,receiveLimit,telephone,salesman,buttonBox);
//                box.setSpacing(20);
//                box.setPadding(new Insets(0,0,0,6));
//                box.setAlignment(Pos.CENTER_LEFT);
//
//                setGraphic(box);
//                setOnMouseEntered(new EventHandler<MouseEvent>() {
//                    @Override
//                    public void handle(MouseEvent event) {
//                        buttonBox.setVisible(true);
//                    }
//                });
//
//                setOnMouseExited(new EventHandler<MouseEvent>() {
//                    @Override
//                    public void handle(MouseEvent event) {
//                        buttonBox.setVisible(false);
//                    }
//                });
//            }
//
//        }
//    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        customerVOList = customerBLService.getCustomerList();
       // setCustomerList();
        

    }

}
