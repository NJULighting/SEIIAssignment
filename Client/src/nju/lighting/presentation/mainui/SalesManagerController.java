package nju.lighting.presentation.mainui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created on 2017/11/27.
 * Description  销售进货人员的主界面
 *
 * @author 高梦婷
 */
public class SalesManagerController extends MainUIController {
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
    public void initialize(URL location, ResourceBundle resources){
        buttons = new Button[]{customerManageBtn,makeSalesDocBtn,makeSalesReturnDocBtn,makeStockDocBtn,makeStockReturnBtn};
        urls = new String[]{"../customerui/CustomerManageUI.fxml","../documentui/SalesDoc.fxml","../documentui/SalesReturnDoc.fxml",
        "../documentui/StockDoc.fxml","../documentui/StockReturnDoc.fxml"};
        MAIN_BUTTON_SIZE = 5;
        super.initialize(location, resources);
    }

}
