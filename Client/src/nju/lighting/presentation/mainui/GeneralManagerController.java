package nju.lighting.presentation.mainui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created on 2017/11/23.
 * Description
 *
 * @author 陈俊宇
 */
public class GeneralManagerController extends MainUIController {

    @FXML
    private Button promotionManageBtn;

    @FXML
    private Button BusinessConditionBtn;

    @FXML
    private Button BusinessHistoryBtn;

    @FXML
    private Button SalesConditionBtn;

    @FXML
    private Button logBtn;

    @FXML
    private Button approvalBtn;

    @FXML
    private Button closeBtn;

    @FXML
    private Button miniBtn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buttons = new Button[]{approvalBtn, promotionManageBtn, BusinessConditionBtn, SalesConditionBtn, logBtn,
                };
        urls = new String[]{"../approvalui/Approval.fxml", "../promotionui/PromotionManageUI.fxml",
                "../documentui/BusinessConditionTable.fxml", "../documentui/SalesDetailTable.fxml",
                "../logui/log.fxml"};
        MAIN_BUTTON_SIZE = 5;
        super.initialize(location, resources);
    }
}
