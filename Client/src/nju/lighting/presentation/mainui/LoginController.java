package nju.lighting.presentation.mainui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import nju.lighting.vo.UserVO;
import shared.Identity;
import shared.LoginReturnState;
import shared.TwoTuple;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created on 2017/11/27.
 * Description
 *
 * @author 陈俊宇
 */
public class LoginController extends CommonFather{
    UserBLService userBLService;


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
        TwoTuple<UserVO, LoginReturnState> result= userBLService.login(account.getText(),password.getText());
        if(result.r.equals( LoginReturnState.SUCCESS)){
            System.out.println("Succ");
            if (result.t.getIdentity().equals( Identity.GENERAL.toString())){
                new MainUI("GeneralManager.fxml");
                System.out.println("General");
            }
            else if(result.t.getIdentity().equals(Identity.REPOSITORY.toString())){
                new MainUI("RepositoryManager.fxml");
                System.out.println("General");
            }

        }else
            System.out.println("Fail");

    }

    @FXML
    public void loginByEnter(KeyEvent event) throws IOException {
        if (event.getCode()== KeyCode.ENTER)
            login();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buttons=new Button[]{closeBtn,miniBtn,loginBtn};
        super.initialize(location, resources);
    }
}