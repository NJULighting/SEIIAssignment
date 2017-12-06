package nju.lighting.presentation.mainui;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created on 2017/12/6.
 * Description
 *
 * @author 陈俊宇
 */
public class Title  extends CommonFather{
    @FXML
    Button close;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buttons=new Button[]{close};
        stage=Client.primaryStage;
        super.initialize(location, resources);
    }
}
