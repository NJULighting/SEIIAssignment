package nju.lighting.presentation.mainui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import shared.Identity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created on 2017/11/23.
 * Description
 *
 * @author 陈俊宇
 */
public class MainUI {
    /**
     * 主界面的容器类，root 包含了 up 和 bottom 两个属性，bottom存于bottoms数组中
     * up为在主界面中上面的部分，bottoms是一个包含下面部分的数组，分别由两个fxml加载出来
     * up部分 对应 MainUIController ，提供了控制bottom 部分跳转的逻辑
     * @throws IOException
     */

    public VBox root;
    public Pane up;
    public Scene scene;
    public Pane[] bottoms;

    static HashMap<Identity,String> hashMap=new HashMap<>();

    static {
        hashMap.put(Identity.GENERAL,"GeneralManager.fxml");
        hashMap.put(Identity.REPOSITORY,"RepositoryManager.fxml");
        hashMap.put(Identity.SALE_MANAGER,"SalesManager.fxml");
    }

    public MainUI(Identity identity) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource(hashMap.get(identity)));
        Pane temp= loader.load();
        MainUIController controller = loader.getController();

        up =temp;
        root=new VBox();
        root.getChildren().add(up);
        bottoms=new Pane[controller.MAIN_BUTTON_SIZE];

        controller.setMainUI(this);
        scene = new Scene(root);
        controller.jumpTo(0);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.getScene().getStylesheets().add(Client.class.getResource("../custom.css").toExternalForm());
        Client.setPrimaryStage(stage);

    }
}
