package nju.lighting.presentation.documentui.costdoc;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import nju.lighting.presentation.mainui.Upper;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateCostDocController implements Initializable,Upper{

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

    }
}
