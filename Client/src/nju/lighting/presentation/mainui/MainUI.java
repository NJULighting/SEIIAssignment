package nju.lighting.presentation.mainui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
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
     *
     * @throws IOException
     */


    static HashMap<Identity, String> hashMap = new HashMap<>();

    static {
        hashMap.put(Identity.GENERAL, "GeneralManager.fxml");
        hashMap.put(Identity.REPOSITORY, "RepositoryManager.fxml");
        hashMap.put(Identity.SALE, "SalesManager.fxml");
        hashMap.put(Identity.SALE_MANAGER, "SalesManager.fxml");
        hashMap.put(Identity.FINANCE, "FinanceManager.fxml");
        hashMap.put(Identity.SYSTEM_ADMIN, "SystemManager.fxml");
    }

    public BorderPane root = new BorderPane();
    private static StackPane stackPane;
    public Pane up;
    public VBox left;
    public Node[] center;
    Stage stage = new Stage();
    TitleController titleController;

    public void setStage() {
        Client.setPrimaryStage(stage);
        titleController.setStage(stage);
    }

    public MainUI(Identity identity) throws IOException {


        FXMLLoader loader = new FXMLLoader(getClass().getResource(hashMap.get(identity)));

        FXMLLoader titleLoader = new FXMLLoader(getClass().getResource("Title.fxml"));
        up = titleLoader.load();
        titleController = titleLoader.getController();
        root.setTop(up);


        Image background = new Image("images/待选背景/蓝色水2.jpg", 1280, 720, false, true);

        BackgroundImage backgroundImage = new BackgroundImage(background,
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

//then you set to your node
        root.setBackground(new Background(backgroundImage));


        left = loader.load();

        root.setLeft(left);
        MainUIController controller = loader.getController();
        center = new Node[controller.MAIN_BUTTON_SIZE];
        controller.setMainUI(this);
//            for (int i=0;i<center.length;i++){
//                controller.jumpTo(i);
//            }
        controller.jumpTo(0);

        stackPane = new StackPane(root);
        stage.setScene(new Scene(stackPane));
        stage.getScene().getStylesheets().add(Client.class.getResource("../custom.css").toExternalForm());

        Image icon = new Image("images/待选背景/简约灯泡.png");
        stage.getIcons().add(icon);

    }

    public static StackPane getStackPane() {
        return stackPane;
    }
}
