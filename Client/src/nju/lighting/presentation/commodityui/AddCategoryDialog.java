package nju.lighting.presentation.commodityui;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;


/**
 * Created on 2017/12/17.
 * Description
 *
 * @author 陈俊宇
 */
public class AddCategoryDialog extends Dialog  {


    @FXML
    JFXTextField textField;





   String getText(){
       return textField.getText();
   }

    @FXML
    void cancel(){
        dialog.close();
    }

    void init(String text){
        textField.setText(text);
    }


}
