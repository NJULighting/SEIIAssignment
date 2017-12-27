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
public class LossAndGainDocMain implements Initializable,Upper{
    @FXML
    HBox container;

    @FXML
    Label sub,title;

    Pane mainPane;

    LossAndGainDocUI controller;


    public void back(){
        setChildren(mainPane,"");
    }


    public void set(Node node) {
        container.getChildren().setAll(node);
    }

    public void setChildren(Node children,String title){
        container.getChildren().setAll(children);
        sub.setText(title);
    }

    public void setAlert(){
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
