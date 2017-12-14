package nju.lighting.presentation.documentui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import nju.lighting.bl.documentbl.DocController;
import nju.lighting.blservice.documentblservice.DocBLService;
import nju.lighting.presentation.mainui.Client;
import nju.lighting.vo.CustomerVO;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.doc.salesdoc.SalesDocVO;
import shared.DocType;
import shared.ResultMessage;
import shared.TwoTuple;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class SalesDocController implements Initializable{
    private CustomerVO customerVO;

    private String salesDocID;

    private double accountBeforeDisNum = 0;

    private double discountNum = 0;

    private double voucherNum = 0;

    private double accountNum = 0;

    private DocBLService docBLService = new DocController();

    @FXML
    private Button choseCustomBtn;

    @FXML
    private Button finishBtn;

    @FXML
    private TextField customer;

    @FXML
    private TextField salesman;

    @FXML
    private TextField userman;

    @FXML
    private TextField repository;

    @FXML
    private TextField accountBeforeDis;

    @FXML
    private TextField discount;

    @FXML
    private TextField voucher;

    @FXML
    private TextField account;

    @FXML
    private TextArea remarks;

    @FXML
    private Label failMessage;

    @FXML
    private Label failDisCount;

    @FXML
    private Label failVoucher;

    @FXML
    private Label failCustomer;

    public void setDiscount(){
        if(checkFormat(discount,failDisCount)){
            discountNum = Double.parseDouble(discount.getText());
            updateAccount();
        }
    }

    public void setVoucher(){
        if(checkFormat(voucher,failVoucher)){
            voucherNum = Double.parseDouble(voucher.getText());
            updateAccount();
        }
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

    //更新最终金额
    public void updateAccount(){
        accountNum = accountBeforeDisNum - discountNum - voucherNum;
        account.setText(String.valueOf(accountNum));
    }

    //完成并提交
    public void finish(){

        if(customerVO==null){
            failCustomer.setVisible(true); //未选择客户
        }
        else if(checkFormat(discount,failDisCount) && checkFormat(voucher,failVoucher)){

            DocVO docVO = docBLService.createDoc(DocType.SALES);


            DocVO salesDocVO = new SalesDocVO(new Date(), Client.getUserVO().getID(),docVO.getDocId(),DocType.SALES
                    ,null,customerVO.getID(),customerVO.getSalesman(),repository.getText(),remarks.getText(),
                    Double.parseDouble(accountBeforeDis.getText()),Double.parseDouble(discount.getText()),
                    Double.parseDouble(voucher.getText()),Double.parseDouble(account.getText()));

            TwoTuple<String,ResultMessage> result =  docBLService.commitDoc(salesDocVO);
            switch (result.r){
                case SUCCESS:
                    salesDocID = result.t;
                    successtoCommit();
                    break;
                case FAILURE:
                    failtoCommit();
                    break;
                default:
                    failtoCommit();
                    break;
            }

        }

    }

    public void successtoCommit(){

    }

    public void failtoCommit(){
        failMessage.setVisible(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userman.setText(Client.getUserVO().getUsername());

        //给discount增加监听，保证数字格式，更新account
        discount.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                setDiscount();
            }
        });

        //给voucher增加监听，保证数字格式，更新account
        voucher.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                setVoucher();
            }
        });

    }
}
