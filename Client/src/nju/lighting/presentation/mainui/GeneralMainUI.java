package nju.lighting.presentation.mainui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * Created on 2017/11/23.
 * Description
 *
 * @author 陈俊宇
 */
public class GeneralMainUI {
    /**
     * 总经理主界面的初始化
     * @throws IOException
     */
    public GeneralMainUI() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GeneralManager.fxml"));
        Pane temp= loader.load();
        GeneralManagerController controller = loader.getController();

        controller.up =temp;
        controller.root.getChildren().add(controller.up);
        controller.scene = new Scene(controller.root);
        controller.jumpTo(0);


    }
}
