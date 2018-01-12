package nju.lighting.presentation.mainui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created on 2017/11/23.
 * Description 总经理的功能栏的控制类，决定了界面跳转
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
        buttons = new Button[]{approvalBtn, promotionManageBtn,BusinessHistoryBtn, BusinessConditionBtn, SalesConditionBtn, logBtn,
        };
        urls = new String[]{"nju/lighting/presentation/approvalui/Approval.fxml", "nju/lighting/presentation/promotionui/PromotionManageUI.fxml",
                "nju/lighting/presentation/documentui/BusinessHistoryTable.fxml","nju/lighting/presentation/documentui/BusinessConditionTable.fxml",
                "nju/lighting/presentation/documentui/SalesDetailTable.fxml", "nju/lighting/presentation/logui/Log.fxml"};
        MAIN_BUTTON_SIZE = urls.length;
        super.initialize(location, resources);
    }
}
