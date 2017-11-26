package nju.lighting.presentation.mainui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created on 2017/11/23.
 * Description
 *
 * @author 陈俊宇
 */
public class MainUI {
    /**
     * 总经理主界面的初始化
     * @throws IOException
     */
    public MainUI(String url) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
        Pane temp= loader.load();
        MainUIController controller = loader.getController();

        controller.up =temp;
        controller.root.getChildren().add(controller.up);

        controller.scene = new Scene(controller.root);
        controller.jumpTo(0);
        Stage stage=new Stage();
        stage.setScene(controller.scene);
        LoginController.setPrimaryStage(stage);

    }
}
