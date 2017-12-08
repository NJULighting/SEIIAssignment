package nju.lighting.presentation.mainui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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
    UserBLService userBLService;
    private static UserVO userVO;


    private double XOffset;
    private double YOffSet;
    private Button[] buttons;


    static Parent root;

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
            root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            primaryStage.setScene(new Scene(root));
            primaryStage.setResizable(false);
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            primaryStage.show();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        launch(args);
    }
}
