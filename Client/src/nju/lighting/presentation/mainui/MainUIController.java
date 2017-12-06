package nju.lighting.presentation.mainui;


import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created on 2017/11/26.
 * Description 四种 MainUI 的抽象父类，提供了下部分界面跳转等方法
 *
 * @author 陈俊宇
 */

public abstract class MainUIController extends CommonFather {

    String[] urls;
    final Button[] currentBtn = new Button[1];
    int MAIN_BUTTON_SIZE;
    MainUI mainUI;  //MainUiController的持有者，因为界面跳转需要控制 mainUI的bottom部分；

    void setMainUI(MainUI mainUI){this.mainUI=mainUI;}

    //界面跳转
    void jumpTo(int index) {
        Pane center;
        String url = urls[index];
        center = mainUI.center[index];


        //设置下半部分界面
        if (center == null) {
            try {
                center = FXMLLoader.load(getClass().getResource(url));
            } catch (IOException e) {
                e.printStackTrace();
            }

            mainUI.center[index]=center;
        }


        mainUI.root.setCenter(center);

        //高光
        if (currentBtn[0] != null) {
            currentBtn[0].setOpacity(MISS_OPACITY);
        } else {
            buttons[index].setOpacity(1);
        }
        currentBtn[0] = buttons[index];
    }

    public void mouseExit(MouseEvent event) {
        if (!((Button) event.getSource()).equals(currentBtn[0]))
        ((Button) event.getSource()).setOpacity(MISS_OPACITY);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        for (int i = 0; i < MAIN_BUTTON_SIZE; i++) {

            int index = i;
            buttons[i].setOpacity(MISS_OPACITY);

            buttons[i].setOnAction(e -> {

                jumpTo(index);
                //Client.setScene(mainUI.scene);
            });

            super.initialize(location, resources);


        }
    }
}
