package nju.lighting.presentation.approvalui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import nju.lighting.presentation.mainui.Client;
import nju.lighting.presentation.mainui.CommonFather;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created on 2017/12/4.
 * Description
 *
 * @author 陈俊宇
 */
public class CommentsController  {


    @FXML
    JFXTextArea comment;




    String getComments(){return comment.getText();}


}
