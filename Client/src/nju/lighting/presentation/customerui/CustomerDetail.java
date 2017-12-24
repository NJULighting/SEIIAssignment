package nju.lighting.presentation.customerui;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.DoubleValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import nju.lighting.blservice.customerblservice.CustomerBLService;
import nju.lighting.presentation.mainui.Client;
import nju.lighting.presentation.mainui.Upper;
import nju.lighting.presentation.utils.CustomerHelper;
import nju.lighting.presentation.utils.TextFieldHelper;
import nju.lighting.vo.CustomerVO;
import shared.CustomerChangeInfo;
import shared.CustomerGrade;
import shared.CustomerType;
import shared.ResultMessage;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created on 2017/12/22.
 * Description
 *
 * @author 陈俊宇
 */
public class CustomerDetail {

    private CustomerBLService customerBLService;

    @FXML
    private JFXComboBox<String> typebox, gradebox;

    @FXML
    private JFXTextField idText, nameText, receiveText, payText, receiveLimitText, salesmanText, telephoneText,
            postageText, emailText, addressText;
    @FXML
    Button modifyButton, saveButton;

    @FXML
    VBox container;

    @FXML
    HBox idBox;


    CustomerSearchListController controller;
    private CustomerVO customerVO ;
    private ArrayList<TextField> textFields = new ArrayList<>();
    Upper upper;
    private boolean add;
    private final double DEFAULT_LIMIT = 2000;

    void init(boolean add) {
        this.add = add;
        textFields.add(nameText);
        textFields.add(receiveText);
        textFields.add(payText);
        textFields.add(salesmanText);
        textFields.add(telephoneText);
        textFields.add(postageText);
        textFields.add(emailText);
        textFields.add(addressText);



        TextFieldHelper.addDoubleValidator(receiveLimitText);
        TextFieldHelper.addDoubleValidator(payText);
        TextFieldHelper.addDoubleValidator(receiveText);
        TextFieldHelper.addRequiredValidator(nameText);
        TextFieldHelper.addRequiredValidator(salesmanText);
        TextFieldHelper.addRequiredValidator(telephoneText);
        TextFieldHelper.addRequiredValidator(addressText);
        TextFieldHelper.addRequiredValidator(postageText);
        TextFieldHelper.addRequiredValidator(emailText);


        //初始化JFXComboBox
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

        //按照item填写每个信息
        if (!add) {
            idText.setText(String.format("%06d", customerVO.getID()));

            if (customerVO.getType() == CustomerType.SALESPERSON)
                typebox.setValue("销售商");
            else
                typebox.setValue("供应商");
            nameText.setText(customerVO.getName());
            gradebox.setValue(CustomerHelper.gradeToString(customerVO.getGrade()));
            receiveText.setText(String.valueOf(customerVO.getReceivable()));
            payText.setText(String.valueOf(customerVO.getPayable()));
            receiveLimitText.setText(String.valueOf(customerVO.getReceivableLimit()));
            telephoneText.setText(customerVO.getTelephone());
            salesmanText.setText(customerVO.getSalesman());

            postageText.setText(customerVO.getPostage());
            emailText.setText(customerVO.getEmail());
            addressText.setText(customerVO.getAddress());
            setEditable(false);
        } else {
            setEditable(true);
            receiveLimitText.setText("" + DEFAULT_LIMIT);
            container.getChildren().remove(idBox);
            typebox.setValue(CustomerHelper.typeToString(CustomerType.SALESPERSON));
            gradebox.setValue(CustomerHelper.gradeToString(CustomerGrade.ONE));
        }


    }

    public void setCustomerVO(CustomerVO customerVO) {
        this.customerVO = customerVO;
    }


    public void back() {
            upper.back();
    }

    //保存失败
    private void failSave() {
    }

    public void save() {
       if(receiveText.validate()&payText.validate()&receiveLimitText.validate() &
               nameText.validate()&salesmanText.validate()&salesmanText.validate()&
               telephoneText.validate()&emailText.validate()&postageText.validate()&
               receiveLimitText.validate()&addressText.validate()){
           if (add)
               saveNew();
           else
               saveModification();
       }
    }

    private void saveNew() {
        CustomerVO customerVO = getCurrentCustomer();
        customerBLService.createCustomer(customerVO);
        TableView tableView = controller.getTableView();
        tableView.getItems().add(getCurrentCustomer());
        back();

    }

    private void saveModification() {
        //更改客户信息 需要调用需要实际的PO，先阶段不能用
//        try {
//            CustomerChangeInfo.Builder builder = new CustomerChangeInfo.Builder(customerVO.getID());
//            builder.changeAddress(addressText.getText())
//                    .changeEmail(emailText.getText())
//                    .changeGrade(CustomerHelper.stringToGrade(gradebox.getValue()))
//                    .changePostage(postageText.getText())
//                    .changeName(nameText.getText())
//                    .changeSalesMan(salesmanText.getText())
//                    .changeTelephone(telephoneText.getText())
//                    .changeType(CustomerHelper.stringToType(typebox.getValue()));
//
//            customerBLService.changeCustomer(builder.build());

//        }catch (RemoteException e){
//            e.printStackTrace();
//            failSave();
//        }

        setEditable(false);

        // 为了 在界面中可以看到
        TableView tableView = controller.getTableView();
        int index = tableView.getItems().indexOf(customerVO);
        tableView.getItems().set(index, getCurrentCustomer());
    }


    //得到现在界面中的客户，用于修改或新建客户
    private CustomerVO getCurrentCustomer() {
        int id = (customerVO == null) ? controller.getTableView().getItems().size()+1 : customerVO.getID();
        return new CustomerVO(
                id,
                CustomerHelper.stringToType(typebox.getValue()),
                CustomerHelper.stringToGrade(gradebox.getValue()),
                nameText.getText(),
                telephoneText.getText(),
                addressText.getText(),
                postageText.getText(),
                emailText.getText(),
                Double.parseDouble(receiveLimitText.getText()),
                Double.parseDouble(receiveText.getText()),
                Double.parseDouble(payText.getText()),
                salesmanText.getText());
    }

    public void modify() {
        setEditable(true);
    }

    void setEditable(boolean editable) {
        modifyButton.setVisible(!editable);
        modifyButton.setDisable(editable);
        saveButton.setDisable(!editable);
        saveButton.setVisible(editable);
        for (int i = 0; i < textFields.size(); i++) {
            textFields.get(i).setEditable(editable);
        }
        if (Client.getUserVO().isAuthority()) {
            receiveLimitText.setEditable(editable);
        } else {
            receiveLimitText.setEditable(!editable);
        }
        gradebox.setDisable(!editable);
        typebox.setDisable(!editable);
    }

    public void delete() {
        ResultMessage resultMessage = customerBLService.deleteCustomer(customerVO.getID());
        if (resultMessage == ResultMessage.SUCCESS) {
            back();
            controller.getTableView().getItems().remove(customerVO);
        } else {
            //删除失败
        }
    }


    public void setUpper(Upper upper) {
        this.upper = upper;
    }

    public void setController(CustomerSearchListController controller) {
        this.controller = controller;
        customerBLService = controller.getCustomerBLService();
    }
}
