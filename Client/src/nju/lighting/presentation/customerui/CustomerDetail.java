package nju.lighting.presentation.customerui;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import nju.lighting.blservice.customerblservice.CustomerBLService;
import nju.lighting.presentation.DialogUI.DialogHelper;
import nju.lighting.presentation.factory.CustomerBLServiceFactory;
import nju.lighting.presentation.factory.UserBLServiceFactory;
import nju.lighting.presentation.mainui.Client;
import nju.lighting.presentation.mainui.MainUI;
import nju.lighting.presentation.mainui.Upper;
import nju.lighting.presentation.utils.CustomerHelper;
import nju.lighting.presentation.utils.TextFieldHelper;
import nju.lighting.presentation.utils.UserHelper;
import nju.lighting.vo.CustomerVO;
import nju.lighting.vo.UserVO;
import shared.*;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Created on 2017/12/22.
 * Description
 *
 * @author 陈俊宇
 */
public class CustomerDetail {

    private final double DEFAULT_LIMIT = 2000;
    @FXML
    Button modifyButton, saveButton, deleteButton;
    @FXML
    VBox container;
    @FXML
    HBox idBox, buttonBox;
    private CustomerBLService customerBLService = CustomerBLServiceFactory.getCustomerBLService();
    @FXML
    private JFXComboBox<String> typeBox, gradeBox;

    @FXML
    JFXComboBox<UserVO> salesman;
    @FXML
    private JFXTextField idText, nameText, receiveText, payText, receiveLimitText, telephoneText,
            postageText, emailText, addressText;
    private CustomerSearchListController controller;
    private CustomerVO customerVO;
    private ArrayList<TextField> textFields = new ArrayList<>();
    private Upper upper;
    private boolean add;

    void init(boolean add) {

        UserHelper.intiUserBox(salesman);

        this.add = add;
        textFields.add(nameText);

        textFields.add(telephoneText);
        textFields.add(postageText);
        textFields.add(emailText);
        textFields.add(addressText);


        TextFieldHelper.addDoubleValidator(receiveLimitText);
        TextFieldHelper.addDoubleValidator(payText);
        TextFieldHelper.addDoubleValidator(receiveText);
        TextFieldHelper.addRequiredValidator(nameText);
        TextFieldHelper.addRequiredValidator(telephoneText);
        TextFieldHelper.addRequiredValidator(addressText);
        TextFieldHelper.addRequiredValidator(postageText);
        TextFieldHelper.addRequiredValidator(emailText);


        //初始化JFXComboBox
        typeBox.getItems().addAll(
                "销售商",
                "供应商"
        );

        gradeBox.getItems().addAll(
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
                typeBox.setValue("销售商");
            else
                typeBox.setValue("供应商");
            nameText.setText(customerVO.getName());
            gradeBox.setValue(CustomerHelper.gradeToString(customerVO.getGrade()));
            receiveText.setText(String.valueOf(customerVO.getReceivable()));
            payText.setText(String.valueOf(customerVO.getPayable()));
            receiveLimitText.setText(String.valueOf(customerVO.getReceivableLimit()));

            salesman.setValue(UserHelper.getSalesman(salesman, customerVO.getSalesman()));

            telephoneText.setText(customerVO.getTelephone());

            postageText.setText(customerVO.getPostage());
            emailText.setText(customerVO.getEmail());
            addressText.setText(customerVO.getAddress());
            setEditable(false);
        } else {
            setEditable(true);
            payText.setText("0.0");
            receiveText.setText("0.0");
            buttonBox.getChildren().remove(deleteButton);
            receiveLimitText.setText("" + DEFAULT_LIMIT);
            container.getChildren().remove(idBox);
            typeBox.setValue(CustomerHelper.typeToString(CustomerType.SALESPERSON));
            gradeBox.setValue(CustomerHelper.gradeToString(CustomerGrade.ONE));
        }


    }

    public void setCustomerVO(CustomerVO customerVO) {
        this.customerVO = customerVO;
    }


    public void back() {
        upper.back();
    }


    public void save() {
        if (receiveText.validate() & payText.validate() & receiveLimitText.validate() &
                nameText.validate() & telephoneText.validate() & salesman.getValue() != null
                & emailText.validate() & postageText.validate()
                & receiveLimitText.validate() & addressText.validate()) {
            if (add)
                saveNew();
            else
                saveModification();
        }
    }

    private void saveNew() {
        CustomerVO customerVO = getCurrentCustomer();
        Result<CustomerVO> addResult = customerBLService.createCustomer(customerVO);
        if (addResult.hasValue()) {
            customerVO = addResult.getValue();
        }
        TableView tableView = controller.getTableView();
        tableView.getItems().add(customerVO);
        back();

    }

    private void saveModification() {
        try {
            CustomerChangeInfo.Builder builder = new CustomerChangeInfo.Builder(customerVO.getID());
            builder.changeAddress(addressText.getText())
                    .changeEmail(emailText.getText())
                    .changeGrade(CustomerHelper.stringToGrade(gradeBox.getValue()))
                    .changePostage(postageText.getText())
                    .changeName(nameText.getText())
                    .changeSalesMan(salesman.getValue().getID())
                    .changeTelephone(telephoneText.getText())
                    .changeType(CustomerHelper.stringToType(typeBox.getValue()));

            ResultMessage result = customerBLService.changeCustomer(builder.build());
            DialogHelper.dialog("修改客户", result, MainUI.getStackPane());
            if (result == ResultMessage.SUCCESS) {
                TableView tableView = controller.getTableView();
                int index = tableView.getItems().indexOf(customerVO);
                CustomerVO afterChanged = getCurrentCustomer();
                afterChanged.setID(customerVO.getID());
                tableView.getItems().set(index, afterChanged);
                back();
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }


    }


    //得到现在界面中的客户，用于修改或新建客户
    private CustomerVO getCurrentCustomer() {
        return new CustomerVO(
                CustomerHelper.stringToType(typeBox.getValue()),
                CustomerHelper.stringToGrade(gradeBox.getValue()),
                nameText.getText(),
                telephoneText.getText(),
                addressText.getText(),
                postageText.getText(),
                emailText.getText(),
                Double.parseDouble(receiveLimitText.getText()),
                Double.parseDouble(receiveText.getText()),
                Double.parseDouble(payText.getText()),
                salesman.getValue().getID()
        );

    }

    public void modify() {
        setEditable(true);
    }

    void setEditable(boolean editable) {
        modifyButton.setVisible(!editable);
        modifyButton.setDisable(editable);
        saveButton.setDisable(!editable);
        saveButton.setVisible(editable);
        for (TextField textField : textFields) {
            textField.setEditable(editable);
        }

        if (!editable)
            receiveLimitText.setEditable(false);
        else {
            if (Client.getUserVO().isAuthority()) {
                receiveLimitText.setEditable(editable);
            } else {
                receiveLimitText.setEditable(!editable);
            }
        }

        gradeBox.setDisable(!editable);
        typeBox.setDisable(!editable);
        salesman.setDisable(!editable);
    }

    public void delete() {
        controller.delete(customerVO);

    }


    public void setUpper(Upper upper) {
        this.upper = upper;
    }

    public void setController(CustomerSearchListController controller) {
        this.controller = controller;
    }
}
