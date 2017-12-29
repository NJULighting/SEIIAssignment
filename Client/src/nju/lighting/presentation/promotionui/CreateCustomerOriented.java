package nju.lighting.presentation.promotionui;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import nju.lighting.presentation.utils.CustomerHelper;
import nju.lighting.presentation.utils.DateHelper;
import nju.lighting.presentation.utils.TextFieldHelper;
import shared.CustomerGrade;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Created on 2017/12/28.
 * Description
 *
 * @author 陈俊宇
 */
public class CreateCustomerOriented implements Initializable {
    @FXML
    JFXComboBox<String> gradePicker;

    @FXML
    JFXTextField offText, voucherText ;


    @FXML
    JFXDatePicker voucherEndDatePicker;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gradePicker.getItems().addAll(CustomerHelper.gradeToString(CustomerGrade.ONE),
                CustomerHelper.gradeToString(CustomerGrade.TWO),
                CustomerHelper.gradeToString(CustomerGrade.THREE),
                CustomerHelper.gradeToString(CustomerGrade.FOUR),
                CustomerHelper.gradeToString(CustomerGrade.FIVE)
        );

        gradePicker.setValue(CustomerHelper.gradeToString(CustomerGrade.ONE));
        TextFieldHelper.addDoubleValidator(offText);
    }

    public double getOff() {
       return TextFieldHelper.getDouble(offText);
    }

    public double getVoucher() {
        return TextFieldHelper.getDouble(voucherText);
    }



    public Date getVoucherEndDate() {
        if (voucherEndDatePicker.getValue() != null&&getVoucher()>0)
            return DateHelper.localDateToDate(voucherEndDatePicker.getValue());
        else
            return null;
    }


}
