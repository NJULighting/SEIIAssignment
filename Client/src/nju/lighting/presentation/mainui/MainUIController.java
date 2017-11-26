package nju.lighting.presentation.mainui;

import com.sun.javafx.robot.impl.FXRobotHelper;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import nju.lighting.bl.userbl.UserBLServie_Stub;
import nju.lighting.blservice.userblservice.UserBLService;
import shared.ResultMessage;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Stack;

/**
 * Created on 2017/11/21.
 * Description
 *
 * @author 陈俊宇
 */
public class MainUIController extends Application implements Initializable{
    UserBLService userBLService;

    private double XOffset;
    private double YOffSet;
    private Button[] buttons;


    static Parent root;

    static Stage primaryStage;

    @FXML
    private TextField account;

    @FXML
    private PasswordField password;

    @FXML
    private  Button closeBtn;

    @FXML
    private Button loginBtn;

    @FXML
    private Button miniBtn;



    @FXML
    public void login() throws IOException {
        userBLService=new UserBLServie_Stub();
        System.out.println(userBLService.logIn(account.getText(),password.getText()));
        if(userBLService.logIn(account.getText(),password.getText()).equals(ResultMessage.SUCCESS)){
            new GeneralMainUI();
        }

    }

    @FXML
    public void getOffSet(MouseEvent event){
        event.consume();
        XOffset=event.getSceneX();
        YOffSet=event.getSceneY();
    }

    @FXML
    public void Drag(MouseEvent event){
        event.consume();
        primaryStage.setX(event.getScreenX() - XOffset);
        primaryStage.setY(event.getScreenY() - YOffSet);

    }
    @FXML
    public void loginByEnter(KeyEvent event) throws IOException {
        if (event.getCode()== KeyCode.ENTER)
            login();
    }

    @FXML
    public void mouseEnter(MouseEvent event){
        ((Button)event.getSource()).setOpacity(1);
        //closeBtn.setOpacity(1);
    }
    @FXML
    public  void mouseExit(MouseEvent event){
        ((Button)event.getSource()).setOpacity(0.5);
    }

    @FXML
    public void close(){
        Platform.exit();
    }

    @FXML
    public void mini(){
        primaryStage.setIconified(true);
    }

    public static void setScene(Scene  scene){
        primaryStage.setScene(scene);
    }

    public static void setPrimaryStage(Stage stage){
        primaryStage.hide();
        primaryStage=stage;
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buttons=new Button[]{loginBtn,closeBtn,miniBtn};


        for(int i=0;i<buttons.length;i++){
            buttons[i].setOnMouseEntered(e->{
                mouseEnter(e);
            });
            buttons[i].setOnMouseExited(e->{
                mouseExit(e);
            });
        }
    }
}
