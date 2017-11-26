package nju.lighting.presentation.mainui;

import com.sun.javafx.robot.impl.FXRobotHelper;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import nju.lighting.bl.userbl.UserBLServie_Stub;
import nju.lighting.blservice.userblservice.UserBLService;
import shared.ResultMessage;


import java.io.IOException;
import java.util.Stack;

/**
 * Created on 2017/11/21.
 * Description
 *
 * @author 陈俊宇
 */
public class MainUIController extends Application {
    UserBLService userBLService;

    private static Parent root;

    private static Stage primaryStage;

    @FXML
    private TextField account;

    @FXML
    private PasswordField password;

    @FXML
    private Button loginBtn;


    @FXML
    public void login() throws IOException {
        userBLService=new UserBLServie_Stub();
        System.out.println(userBLService.logIn(account.getText(),password.getText()));
        if(userBLService.logIn(account.getText(),password.getText()).equals(ResultMessage.SUCCESS)){
            new GeneralMainUI();
        }

    }

    public static void setScene(Scene  scene){
        primaryStage.setScene(scene);
    }

    @Override
    public void start(Stage primaryStage){

        this.primaryStage=primaryStage;

        try{
            root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            primaryStage.setScene(new Scene(root));
            primaryStage.setResizable(false);
            primaryStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    public static void main(String[] args){
        launch(args);
    }
}
