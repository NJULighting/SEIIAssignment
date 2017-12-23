package nju.lighting.presentation.customerui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created on 2017/12/23.
 * Description
 *
 * @author 陈俊宇
 */
public class CustomerPicker implements Initializable{

    @FXML
    Button backButton;

    @FXML
    Pane pane;

    Node previous;
    Label title;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("CustomerSearchListUI.fxml"));
        try {
            pane.getChildren().add(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

        CustomerSearchListController controller=loader.getController();
        controller.setReadOnly();

    }

    public void back() {
//        father.getChildren().clear();
//        father.getChildren().add(backPane);
        title.setText("客户管理");
    }
    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    public void setTitle(Label title) {
        this.title = title;
    }
}
