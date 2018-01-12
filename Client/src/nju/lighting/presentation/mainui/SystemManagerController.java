package nju.lighting.presentation.mainui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created on 2017/12/18.
 * Description
 * @author 陈俊宇
 */
public class SystemManagerController extends MainUIController {

    @FXML
    private Button userManageBtn;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buttons = new Button[]{userManageBtn};
        urls = new String[]{"nju/lighting/presentation/userui/UserMain.fxml"};
        MAIN_BUTTON_SIZE = urls.length;
        super.initialize(location, resources);
    }
}
