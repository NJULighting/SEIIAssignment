package nju.lighting.presentation.documentui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import nju.lighting.presentation.mainui.Upper;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created on 2017/12/25.
 * Description
 *制定报损报溢单的主界面，库存人员界面上制定报损报溢单即跳转到此界面
 * @author 陈俊宇
 */
public class LossAndGainDocMain implements Initializable{
    @FXML
    HBox container;

    @FXML
    Label sub,title;

    Pane mainPane;

    LossAndGainDocUI controller;


    public void back(){
        setChildren(mainPane,"");
    }

    public void setChildren(Node children,String title){
        container.getChildren().clear();
        container.getChildren().add(children);
        sub.setText(title);
    }

    void setAlert(){
        title.setText("制定报警单");
        controller.setAlert();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("LossAndGainDocUI.fxml"));
        try {
            mainPane=loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        container.getChildren().add(mainPane);
         controller=loader.getController();
        controller.setMain(this);
    }
}
