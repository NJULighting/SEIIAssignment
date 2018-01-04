package nju.lighting.presentation.mainui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class AccountManagerController extends MainUIController {
    @FXML
    private Button accountManage;
    @FXML
    private Button makeAccountIODoc;
    @FXML
    private Button makeCostDoc;
    @FXML
    private Button businessConditionBtn;
    @FXML
    private Button salesConditionBtn;
    @FXML
    private Button businessHistoryBtn;
    @FXML
    private Button initaccountBtn;
    @FXML
    private Button logBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buttons = new Button[]{accountManage, makeAccountIODoc, makeCostDoc, businessConditionBtn, salesConditionBtn, businessHistoryBtn,
                initaccountBtn, logBtn};
        urls = new String[]{"../accountui/Account.fxml", "../documentui/accountiodoc/AddAccountIODoc.fxml", "../documentui/costdoc/CreateCostDoc.fxml",
                "../documentui/BusinessConditionTable.fxml", "../documentui/SalesConditionTable.fxml", "../documentui/HistoryDoc",
                "../initui/InitAccount.fxml", "../logui/Log.fxml"};
        MAIN_BUTTON_SIZE = 8;
        super.initialize(location, resources);
    }
}
