package nju.lighting.presentation.customerui;

import com.jfoenix.controls.JFXTreeViewPath;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomerManageController implements Initializable{

    @FXML
    HBox child;

    @FXML
    Pane mainPane;

    @FXML
    Label sub;

    @FXML
    void back(){
        child.getChildren().clear();
        child.getChildren().add(mainPane);
        sub.setText("");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("CustomerSearchListUI.fxml"));
            loader.load();
            CustomerSearchListController controller=loader.getController();
            mainPane=controller.getPane();
            child.getChildren().add(mainPane);



            controller.title=sub;
            controller.father=child;

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}