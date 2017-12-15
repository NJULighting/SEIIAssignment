package nju.lighting.presentation.commodityui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created on 2017/12/14.
 * Description
 *
 * @author 陈俊宇
 */
public class CommodityManage implements Initializable{

    @FXML
    AnchorPane container;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {

            CommodityCategory.setEditable(true);

            FXMLLoader loader=new FXMLLoader(getClass().getResource("CommodityCateGory.fxml"));

            container.getChildren().add(loader.load());

            CommodityCategory controller =loader.getController();
            //controller.setMinHeight(680);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
