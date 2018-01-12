package nju.lighting.presentation.mainui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import nju.lighting.vo.UserVO;

import java.io.IOException;

/**
 * Created on 2017/11/21.
 * Description 程序主类
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
