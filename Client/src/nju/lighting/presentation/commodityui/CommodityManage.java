package nju.lighting.presentation.commodityui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeView;
import javafx.scene.layout.Pane;
import nju.lighting.vo.commodity.Nameable;

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
    Pane container;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {


            FXMLLoader loader=new FXMLLoader(getClass().getResource("CommodityCateGory.fxml"));

            container.getChildren().add(loader.load());

            CommodityCategory controller= loader.getController();

            controller.categoryTreeView.setCellFactory((TreeView<Nameable> p) ->
                    new MyTreeCell(controller));
            controller.categoryTreeView.setMinHeight(680);
            controller.topPadding=50;
            controller.leftPadding=300;


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
