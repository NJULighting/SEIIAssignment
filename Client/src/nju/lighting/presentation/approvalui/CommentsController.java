package nju.lighting.presentation.approvalui;

import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;

/**
 * Created on 2017/12/4.
 * Description
 * @author 陈俊宇
 */
public class CommentsController {


    @FXML
    JFXTextArea comment;


    String getComments() {
        return comment.getText();
    }


}
