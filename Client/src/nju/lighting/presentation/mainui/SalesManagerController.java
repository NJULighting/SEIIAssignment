package nju.lighting.presentation.mainui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created on 2017/11/27.
 * Description  销售进货人员的主界面
 * @author 高梦婷
 */
public class SalesManagerController extends MainUIController {
    @FXML
    private Button mainBtn;
    @FXML
    private Button customerManageBtn;
    @FXML
    private Button makeSalesDocBtn;
    @FXML
    private Button makeSalesReturnDocBtn;
    @FXML
    private Button makeStockDocBtn;
    @FXML
    private Button makeStockReturnBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buttons = new Button[]{mainBtn, customerManageBtn, makeSalesDocBtn, makeSalesReturnDocBtn, makeStockDocBtn, makeStockReturnBtn};
        urls = new String[]{"../homeui/SalesManagerHome.fxml", "../customerui/CustomerManageUI.fxml", "../documentui/SalesDoc.fxml", "../documentui/AddSalesReturnDoc.fxml",
                "../documentui/stockdoc/AddStockDoc.fxml", "../documentui/stockdoc/AddStockReturnDoc.fxml",};
        MAIN_BUTTON_SIZE = urls.length;
        super.initialize(location, resources);
    }

}
