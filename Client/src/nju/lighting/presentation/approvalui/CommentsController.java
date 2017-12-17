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
public class CommentsController extends CommonFather {
    @FXML
    JFXButton okBtn;

    @FXML
    JFXButton cancelBtn;

    @FXML
    JFXTextArea comment;

    ApprovalUIController approvalUIController;

    Stage dialog;



    public void setApprovalUIController(ApprovalUIController approvalUIController) {
        this.approvalUIController = approvalUIController;
    }

    @FXML
    void ok() {
        approvalUIController.setCancel(false);
        approvalUIController.setComment(comment.getText());
        dialog.hide();


    }

    @FXML
    void cancel() {
        approvalUIController.setCancel(true);
        dialog.hide();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(dialog);

        stage = dialog;
        MISS_OPACITY = 0.9;
        buttons = new Button[]{okBtn, cancelBtn};
        super.initialize(location, resources);
    }

    public void setDialog(Stage dialog) {
        this.dialog = dialog;
    }
}
