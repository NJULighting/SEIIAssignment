package nju.lighting.presentation.customerui;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import nju.lighting.bl.customerbl.CustomerBLService_Stub;
import nju.lighting.blservice.customerblservice.CustomerBLService;
import nju.lighting.presentation.mainui.Client;
import nju.lighting.vo.CustomerVO;

import shared.*;

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
    private Pane firstPane;

    @FXML
    private Pane secondPane;

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


    //=======secondPane=======//

    private CustomerVO customerVO; //secondPane 展示的CustomerVO
    private ArrayList<TextField> textFields = new ArrayList<>();//存储可以更改的textfield

    @FXML
    private JFXComboBox<String> typebox;

    @FXML
    private JFXComboBox<String> gradebox;

    @FXML
    private TextField idText;

    @FXML
    private TextField nameText;

    @FXML
    private TextField receiveText;

    @FXML
    private TextField payText;

    @FXML
    private TextField receiveLimitText;

    @FXML
    private TextField salesmanText;

    @FXML
    private TextField telephoneText;

    @FXML
    private TextField postageText;

    @FXML
    private TextField emailText;

    @FXML
    private TextField addressText;

    @FXML
    private Button backButton;

    @FXML
    private Button modifyButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button saveButton;

    @FXML
    private Label failDisCount1;

    @FXML
    private Label failDisCount2;

    @FXML
    private Label failDisCount3;

    @FXML
    private Label failDisCount4;


    private void setName(){
        if(nameText.getText()==null||nameText.getText().length()==0){
            failDisCount4.setVisible(true);
            nameText.requestFocus();
        }else{
            failDisCount4.setVisible(false);
        }

    }

    private void setPay(){
        checkFormat(payText,failDisCount1);
    }

    private void setReceive(){
        checkFormat(receiveText,failDisCount2);
    }

    private void setReceiveLimit(){
        checkFormat(receiveLimitText,failDisCount3);
    }

    //检查是否是数字格式，若否则重新focus
    public boolean checkFormat(TextField textField,Label label){
        boolean march = true;
        try{
            double temp = Double.parseDouble(textField.getText());
        }catch (NumberFormatException e){
            label.setVisible(true);
            textField.requestFocus();
            march = false;
        }

        if(march)
            label.setVisible(false);

        return march;
    }

    public void back() {
        secondPane.setVisible(false);
        secondPane.setDisable(true);
        firstPane.setDisable(false);
        firstPane.setVisible(true);
    }

    //保存失败
    private void failSave(){

    }

    public void save(){
        //更改客户信息
        try {
            CustomerChangeInfo.Builder builder = new CustomerChangeInfo.Builder(customerVO.getID());
            builder.changeAddress(addressText.getText());
            builder.changeEmail(emailText.getText());
            CustomerGrade grade;
            switch (gradebox.getValue()){
                case "一级":
                    grade = CustomerGrade.ONE;
                    break;
                case "二级":
                    grade = CustomerGrade.TWO;
                    break;
                case "三级":
                    grade = CustomerGrade.THREE;
                    break;
                case "四级":
                    grade = CustomerGrade.FOUR;
                    break;
                case "五级":
                    grade = CustomerGrade.FIVE;
                    break;
                default:
                    grade = CustomerGrade.ONE;
                    break;
            }
            builder.changeGrade(grade);
            builder.changePostage(postageText.getText());
            builder.changeName(nameText.getText());
            builder.changeSalesMan(salesmanText.getText());
            customerBLService.changeCustomer(builder.build());
        }catch (RemoteException e){
            e.printStackTrace();
            failSave();
        }
        saveButton.setVisible(false);
        saveButton.setDisable(true);
        modifyButton.setVisible(true);
        modifyButton.setDisable(false);
        for(int i=0;i<textFields.size();i++){
            textFields.get(i).setEditable(false);
        }
        receiveLimitText.setEditable(false);
    }

    public void modify(){
        modifyButton.setVisible(false);
        modifyButton.setDisable(true);
        saveButton.setDisable(false);
        saveButton.setVisible(true);
        for(int i=0;i<textFields.size();i++){
            textFields.get(i).setEditable(true);
        }
        if(Client.getUserVO().isAuthority()){
            receiveLimitText.setEditable(true);
        }else{
            receiveLimitText.setEditable(false);
        }
    }

    public void delete(){
        ResultMessage resultMessage = customerBLService.deleteCustomer(customerVO.getID());
        if(resultMessage == ResultMessage.SUCCESS){
            back();
        }
        else{
            //删除失败
        }
    }

    private void initializeSecondPane() {
        //初始化JFXComboBox

        //按照item填写每个信息
        idText.setText(String.format("%06d", customerVO.getID()));

        if(customerVO.getType()== CustomerType.SALESPERSON)
            typebox.setValue("销售商");
        else
            typebox.setValue("供应商");

        nameText.setText(customerVO.getName());

        switch (customerVO.getGrade()){
            case ONE:
                gradebox.setValue("一级");
                break;
            case TWO:
                gradebox.setValue("二级");
                break;
            case THREE:
                gradebox.setValue("三级");
                break;
            case FOUR:
                gradebox.setValue("四级");
                break;
            case FIVE:
                gradebox.setValue("五级(vip)");
                break;
            default:
                gradebox.setValue(" ");
                break;
        }

        receiveText.setText(String.valueOf(customerVO.getReceivable()));
        payText.setText(String.valueOf(customerVO.getPayable()));
        receiveLimitText.setText(String.valueOf(customerVO.getReceivableLimit()));
        telephoneText.setText(customerVO.getTelephone());
        salesmanText.setText(customerVO.getSalesman());

        postageText.setText(customerVO.getPostage());
        emailText.setText(customerVO.getEmail());
        addressText.setText(customerVO.getAddress());


    }

    //=========secondPane===========//


    //清除搜索，列表显示所有客户
    public void setDeleteSearch(){
        search.setText("");
        customerVOList = customerBLService.getCustomerList();
        setCustomerList();

        noResult.setVisible(false);

        deleteSearch.setDisable(true);
        deleteSearch.setVisible(false);
    }

    //关键字字数不超过8个字
    public void keyTyped(Event e){
        String s = search.getText();
        if(s.length() >= 8) e.consume();
    }

    public void search(){
        String keywords = search.getText();
        if(keywords==null||keywords.length()==0){//无关键词，则显示所有列表
            customerVOList = customerBLService.getCustomerList();
            setCustomerList();
        }
        else{//有关键词，删除搜索键可见
            deleteSearch.setDisable(false);
            deleteSearch.setVisible(true);

            List<CustomerVO> customerVOS = customerBLService.search(keywords);
            if(customerVOS!=null){
                noResult.setVisible(false);
                customerVOList = customerVOS;
                setCustomerList();
            }else{
                noResult.setVisible(true);
            }
        }
    }

    //设置客户列表的显示
    private void setCustomerList(){
        customerList.setPageCount((customerVOList.size() / itemsPerPage) + 1);
        customerList.setPageFactory((Integer index) -> createPage(index));
    }

    ListView createPage(int index) {
        ListView<CustomerVO> customerVOJFXListView = new JFXListView<>();
        ObservableList<CustomerVO> information = FXCollections.observableArrayList();

        int left = customerVOList.size() - index * itemsPerPage;
        int max = (left > itemsPerPage) ? itemsPerPage : left;

        for (int i = 0; i < max; i++) {
            information.add(customerVOList.get(index * itemsPerPage + i));
        }

        customerVOJFXListView.setItems(information);
        customerVOJFXListView.setCellFactory((ListView<CustomerVO> l) -> new myListCell());
        System.out.println(customerVOJFXListView.getStylesheets());
        System.out.println(customerVOJFXListView.getStylesheets());

        return customerVOJFXListView;
    }


    class myListCell extends ListCell<CustomerVO> {
        Label id,type,name,grade,receive,pay,receiveLimit,telephone,salesman;
        HBox buttonBox;
        JFXButton openBtn,invalidBtn;
        HBox box;
        Pane customerMessage;

        @Override
        public void updateItem(CustomerVO item, boolean empty) {
            super.updateItem(item,empty);
            if (item != null) {
                id = new Label(String.format("%06d",item.getID()));
                id.setPrefWidth(60);

                if(item.getType()== CustomerType.SALESPERSON)
                    type = new Label("销售商");
                else
                    type = new Label("供应商");
                type.setPrefWidth(45);

                name = new Label(item.getName());
                name.setPrefWidth(60);

                switch (item.getGrade()){
                    case ONE:
                        grade = new Label("一级");
                        break;
                    case TWO:
                        grade = new Label("二级");
                        break;
                    case THREE:
                        grade = new Label("三级");
                        break;
                    case FOUR:
                        grade = new Label("四级");
                        break;
                    case FIVE:
                        grade = new Label("五级(vip)");
                        break;
                    default:
                        grade = new Label(" ");
                        break;
                }
                grade.setPrefWidth(82);

                receive = new Label(String.valueOf(item.getReceivable()));
                receive.setPrefWidth(74);
                pay = new Label(String.valueOf(item.getPayable()));
                pay.setPrefWidth(74);
                receiveLimit = new Label(String.valueOf(item.getReceivableLimit()));
                receiveLimit.setPrefWidth(74);
                telephone = new Label(item.getTelephone());
                telephone.setPrefWidth(97);
                salesman = new Label(item.getSalesman());
                salesman.setPrefWidth(75);

                openBtn = new JFXButton("查看");
                openBtn.setTextFill(Color.valueOf("#5599ff"));
                invalidBtn = new JFXButton("删除");
                invalidBtn.setTextFill(Color.valueOf("#5599ff"));
                buttonBox = new HBox(openBtn,invalidBtn);
                buttonBox.setAlignment(Pos.CENTER_RIGHT);
                buttonBox.setSpacing(20);
                buttonBox.setPadding(new Insets(0,0,0,15));
                buttonBox.setVisible(false);

                //查看客户
                openBtn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        firstPane.setVisible(false);
                        firstPane.setDisable(true);

                        customerVO = item;
                        initializeSecondPane();

                        secondPane.setDisable(false);
                        secondPane.setVisible(true);

                    }
                });

                invalidBtn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        CustomerBLService customerBLService = new CustomerBLService_Stub();
                        ResultMessage resultMessage = customerBLService.deleteCustomer(Integer.parseInt(id.getText()));
                        System.out.println("delete customer: "+resultMessage);
                        //CustomerManageController
                    }
                });

                box = new HBox(id,type,name,grade,receive,pay,receiveLimit,telephone,salesman,buttonBox);
                box.setSpacing(20);
                box.setPadding(new Insets(0,0,0,6));
                box.setAlignment(Pos.CENTER_LEFT);

                setGraphic(box);
                setOnMouseEntered(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        buttonBox.setVisible(true);
                    }
                });

                setOnMouseExited(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        buttonBox.setVisible(false);
                    }
                });
            }

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {



        customerVOList = customerBLService.getCustomerList();

        setCustomerList();


        //combo初始化
        typebox.getItems().addAll(
                "销售商",
                "供应商"
        );

        gradebox.getItems().addAll(
                        "一级",
                        "二级",
                        "三级",
                        "四级",
                        "五级(vip)"
        );

        //把可更改项给textfileds
        textFields.add(nameText);
        textFields.add(receiveText);
        textFields.add(payText);
        textFields.add(salesmanText);
        textFields.add(telephoneText);
        textFields.add(postageText);
        textFields.add(emailText);
        textFields.add(addressText);


        //给pay增加监听，保证数字格式
        payText.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                setPay();
            }
        });

        //给receive增加监听，保证数字格式
        receiveText.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                setReceive();
            }
        });

        //给receiveLimit增加监听，保证数字格式
        receiveLimitText.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                setReceiveLimit();
            }
        });

        //给name增加监听，保证不为空
        nameText.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                setName();
            }
        });

    }

}
