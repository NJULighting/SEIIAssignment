package nju.lighting.presentation.mainui;

import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTreeView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import shared.Identity;
import sun.dc.pr.PRError;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * Created on 2017/12/18.
 * Description
 *
 * @author 陈俊宇
 */
public class SystemManagerController extends MainUIController {

    @FXML
    private Button userManageBtn;

    @FXML
    private Button logBtn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buttons = new Button[]{userManageBtn, logBtn,
        };
        urls = new String[]{"../userui/UserMain.fxml", "../logui/log.fxml"};
        MAIN_BUTTON_SIZE = 2;
        super.initialize(location, resources);
    }
}
