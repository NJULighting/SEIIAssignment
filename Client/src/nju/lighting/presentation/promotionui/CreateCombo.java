package nju.lighting.presentation.promotionui;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import nju.lighting.presentation.utils.TextFieldHelper;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created on 2017/12/28.
 * Description 创建组合包的控制类
 * @author 陈俊宇
 */
public class CreateCombo implements Initializable {

    @FXML
    JFXTextField offText;


    public double getOff() {
        return TextFieldHelper.getDouble(offText);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TextFieldHelper.addDoubleValidator(offText);
    }
}
