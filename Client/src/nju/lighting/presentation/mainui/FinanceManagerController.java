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
        urls = new String[]{"nju/lighting/presentation/homeui/HomePage.fxml","nju/lighting/presentation/accountui/Account.fxml",
                "nju/lighting/presentation/documentui/accountiodoc/AddAccountIODoc.fxml",
                "nju/lighting/presentation/documentui/costdoc/AddCostDoc.fxml",
                "nju/lighting/presentation/documentui/BusinessConditionTable.fxml",
                "nju/lighting/presentation/documentui/SalesDetailTable.fxml",
                "nju/lighting/presentation/documentui/BusinessHistoryTable.fxml",
                "nju/lighting/presentation/initui/InitAccount.fxml", "nju/lighting/presentation/logui/Log.fxml"};
        MAIN_BUTTON_SIZE = urls.length;
        super.initialize(location, resources);
    }
}
