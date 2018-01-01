package nju.lighting.presentation.mainui;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created on 2017/12/6.
 * Description
 *
 * @author 高梦婷
 */
public class TitleController extends CommonFather{
    @FXML
    Label userName;
    @FXML
    Label userID;
    @FXML
    Button close;
    @FXML
    Button mini;
    @FXML
    Button exit;

    public void exit() throws IOException{
        Stage stage =new Stage();
        AnchorPane root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        stage.setScene(new Scene(root));
        Client.setPrimaryStage(stage);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buttons=new Button[]{close,mini,exit};
        stage=Client.primaryStage;
        super.initialize(location, resources);
        userName.setText(Client.getUserVO().getUsername());
        userID.setText(Client.getUserVO().getID());
    }
}
