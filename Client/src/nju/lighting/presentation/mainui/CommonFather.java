package nju.lighting.presentation.mainui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import nju.lighting.bl.userbl.UserController;
import nju.lighting.blservice.userblservice.LoginService;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created on 2017/11/27.
 * Description 所有界面的公共父类，提供了关闭、最小化窗口，拖动，按钮高光等方法
 * @author 陈俊宇
 */
public abstract class CommonFather implements Initializable {
    public Stage stage;
    public Button[] buttons;
    public Double MISS_OPACITY = 0.45;
    private double XOffset;
    private double YOffSet;

    //关闭程序
    @FXML
    void close() {
        Platform.exit();
        System.out.println("close");
        LoginService loginService = new UserController();
        loginService.logout();
    }

    //窗口最小化
    @FXML
    void mini() {
        Client.primaryStage.setIconified(true);
    }


    public void mouseEnter(MouseEvent event) {
        ((Button) event.getSource()).setOpacity(1);
    }


    public void mouseExit(MouseEvent event) {
        ((Button) event.getSource()).setOpacity(MISS_OPACITY);
    }

    @FXML
    public void getOffSet(MouseEvent event) {
        event.consume();
        XOffset = event.getSceneX();
        YOffSet = event.getSceneY();
    }

    @FXML
    public void Drag(MouseEvent event) {
        event.consume();
        stage.setX(event.getScreenX() - XOffset);
        stage.setY(event.getScreenY() - YOffSet);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        for (int i = 0; i < buttons.length; i++) {

            //鼠标经过按钮时高光
            buttons[i].setOnMouseEntered(e -> {
                mouseEnter(e);
            });
            buttons[i].setOnMouseExited(e -> {
                mouseExit(e);
            });
        }

    }


}
