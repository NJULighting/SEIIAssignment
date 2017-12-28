package nju.lighting.presentation.promotionui;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import nju.lighting.presentation.utils.DateHelper;
import nju.lighting.presentation.utils.TextFieldHelper;

import javax.xml.soap.Text;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Created on 2017/12/17.
 * Description
 *
 * @author 陈俊宇
 */
public class CreatePriceOriented implements Initializable {



    @FXML
    JFXDatePicker voucherEndDatePicker;

    @FXML
    JFXTextField priceText, voucherText ;

    public double getPrice() {
        return TextFieldHelper.getDouble(priceText);
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TextFieldHelper.addDoubleValidator(priceText);
        TextFieldHelper.addDoubleValidator(voucherText);


    }
}
