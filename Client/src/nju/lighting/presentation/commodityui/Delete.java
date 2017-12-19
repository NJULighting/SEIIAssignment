package nju.lighting.presentation.commodityui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Created on 2017/12/18.
 * Description
 *
 * @author 陈俊宇
 */
public class Delete extends Dialog {

    @FXML
    Label label;

    void setText(String text){
        label.setText(text);
    }
}
