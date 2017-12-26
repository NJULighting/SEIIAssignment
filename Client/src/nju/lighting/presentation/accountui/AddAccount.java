package nju.lighting.presentation.accountui;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import nju.lighting.presentation.utils.TextFieldHelper;
import nju.lighting.vo.account.AccountVO;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created on 2017/12/26.
 * Description
 *
 * @author 陈俊宇
 */
public class AddAccount implements Initializable {


    @FXML
    JFXTextField idText,nameText,amountText;

    @FXML
    Label amountLabel;

    @FXML
    VBox labelBox,textBox;

    AccountVO getAccount(){
        if (idText.validate()&nameText.validate()&amountText.validate()){
            return new AccountVO(
                    idText.getText(),
                    nameText.getText(),
                    Double.parseDouble(amountText.getText()),
                    null
            );
        }
        return null;
    }

    String getName(){return nameText.getText();}
    String getId(){return idText.getText();}
    Double getAmount(){return Double.parseDouble(amountText.getText());}
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TextFieldHelper.addRequiredValidator(idText);
        TextFieldHelper.addRequiredValidator(nameText);
        TextFieldHelper.addDoubleValidator(amountText);
    }
    void setModify(){
        labelBox.getChildren().remove(amountLabel);
        textBox.getChildren().remove(amountText);
    }
}
