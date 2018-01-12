package nju.lighting.presentation.promotionui;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import nju.lighting.presentation.utils.CustomerHelper;
import nju.lighting.presentation.utils.DateHelper;
import nju.lighting.presentation.utils.TextFieldHelper;
import shared.CustomerGrade;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Created on 2017/12/28.
 * Description 创建针对用户的促销策略的控制类
 * @author 陈俊宇
 */
public class CreateCustomerOriented implements Initializable {
    @FXML
    JFXComboBox<CustomerGrade> gradePicker;

    @FXML
    JFXTextField offText, voucherText;


    @FXML
    JFXDatePicker voucherEndDatePicker;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gradePicker.getItems().addAll(CustomerGrade.values());

        gradePicker.setValue(gradePicker.getItems().get(0));
        TextFieldHelper.addDoubleValidator(offText);
    }

    public double getOff() {
        return TextFieldHelper.getDouble(offText);
    }

    public double getVoucher() {
        return TextFieldHelper.getDouble(voucherText);
    }

    CustomerGrade getLevel(){return  gradePicker.getValue();}


    Date getVoucherEndDate() {
        if (voucherEndDatePicker.getValue() != null && getVoucher() > 0)
            return DateHelper.localDateToDate(voucherEndDatePicker.getValue());
        else
            return null;
    }


}
