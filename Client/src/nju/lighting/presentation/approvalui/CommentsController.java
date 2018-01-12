package nju.lighting.presentation.approvalui;

import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;

/**
 * Created on 2017/12/4.
 * Description 填写审批单据界面的控制类
 * @author 陈俊宇
 */
public class CommentsController {
    @FXML
    JFXTextArea comment;

    String getComments() {
        return comment.getText();
    }
}
