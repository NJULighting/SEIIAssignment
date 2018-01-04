package nju.lighting.presentation.commodityui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created on 2017/12/14.
 * Description
 * @author 陈俊宇
 */
public class CommodityManage implements Initializable {
    @FXML
    StackPane stackPane;

    @FXML
    HBox container;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {


            FXMLLoader loader = new FXMLLoader(getClass().getResource("CommodityCategory.fxml"));

            container.getChildren().add(loader.load());
            CommodityCategory controller = loader.getController();
            controller.setStackPane(stackPane);
            controller.setEditable();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
