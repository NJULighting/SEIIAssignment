package nju.lighting.presentation.mainui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class FinanceManagerController extends MainUIController {
    @FXML
    private Button accountManage,makeAccountIODoc,makeCostDoc,businessConditionBtn,
            salesConditionBtn,businessHistoryBtn,initaccountBtn, logBtn,home;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buttons = new Button[]{home,accountManage, makeAccountIODoc, makeCostDoc, businessConditionBtn,
                salesConditionBtn, businessHistoryBtn, initaccountBtn, logBtn};
        urls = new String[]{"../homeui/HomePage.fxml","../accountui/Account.fxml",
                "../documentui/accountiodoc/AddAccountIODoc.fxml",
                "../documentui/costdoc/AddCostDoc.fxml",
                "../documentui/BusinessConditionTable.fxml",
                "../documentui/SalesDetailTable.fxml",
                "../documentui/BusinessHistoryTable.fxml",
                "../initui/InitAccount.fxml", "../logui/Log.fxml"};
        MAIN_BUTTON_SIZE = urls.length;
        super.initialize(location, resources);
    }
}
