package nju.lighting.presentation.mainui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import nju.lighting.bl.userbl.UserBLServie_Stub;
import nju.lighting.blservice.userblservice.UserBLService;
import nju.lighting.presentation.utils.DateHelper;
import nju.lighting.vo.UserVO;
import shared.Identity;
import shared.LoginReturnState;
import shared.TwoTuple;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created on 2017/11/21.
 * Description
 *
 * @author 陈俊宇
 */
public class Client extends Application {
    private static UserVO userVO;



    static Stage primaryStage;

    public static void setScene(Scene  scene){
        primaryStage.setScene(scene);
    }

    public static void setPrimaryStage(Stage stage){
        primaryStage.hide();
        primaryStage=stage;
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();

    }


    public static UserVO getUserVO() {
        return userVO;
    }

    public static void setUserVO(UserVO userVO) {
        Client.userVO = userVO;
    }

    @Override
    public void start(Stage primaryStage){

        this.primaryStage=primaryStage;

        try{
            AnchorPane node = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Image icon = new Image("images/待选背景/简约灯泡.png");
            primaryStage.setScene(new Scene(node));
            primaryStage.setResizable(false);
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            primaryStage.getIcons().add(icon);
            primaryStage.show();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        launch(args);
    }
}
