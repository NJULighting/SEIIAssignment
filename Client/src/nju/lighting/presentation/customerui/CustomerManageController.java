package nju.lighting.presentation.customerui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerManageController implements Initializable{

    @FXML
    private Pane childPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            childPane.getChildren().add(FXMLLoader.load(getClass().getResource("CustomerSearchListUI.fxml")));

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}