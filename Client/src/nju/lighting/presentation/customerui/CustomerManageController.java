package nju.lighting.presentation.customerui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import nju.lighting.presentation.mainui.Upper;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerManageController implements Initializable, Upper {

    @FXML
    HBox child;

    @FXML
    Pane mainPane;

    @FXML
    Label sub;


    public void back() {
        child.getChildren().clear();
        child.getChildren().add(mainPane);
        sub.setText("");
    }

    @Override
    public void setChildren(Node node, String title) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerSearchListUI.fxml"));
            mainPane = loader.load();
            CustomerSearchListController controller = loader.getController();
            child.getChildren().add(mainPane);

            controller.setUpper(this);
            controller.title = sub;
            controller.father = child;

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}