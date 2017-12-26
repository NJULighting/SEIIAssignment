package nju.lighting.presentation.documentui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created on 2017/12/26.
 * Description
 *
 * @author 陈俊宇
 */
public class AlertDocMain implements Initializable {
    @FXML
    Pane container;

    LossAndGainDocMain controller;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("LossAndGainDocMain.fxml"));
        try {
           container.getChildren().add(loader.load());

        } catch (IOException e) {
            e.printStackTrace();
        }
        controller=loader.getController();
        controller.setAlert();
    }
}
