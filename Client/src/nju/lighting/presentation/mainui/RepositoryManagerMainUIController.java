package nju.lighting.presentation.mainui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created on 2017/11/26.
 * Description  库存管理人员的主界面
 *
 * @author 陈俊宇
 */
public class RepositoryManagerMainUIController extends MainUIController{
    @FXML
    Button CommodityBtn;

    @FXML
    Button HistoryBtn;

    @FXML
    Button miniBtn;

    @FXML
    Button closeBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buttons = new Button[]{HistoryBtn,CommodityBtn,miniBtn,closeBtn};
        urls = new String[]{"../documentui/HistoryDoc.fxml","../commodityui/CommodityUI.fxml"};
        MAIN_BUTTON_SIZE = 2;
        super.initialize(location, resources);
    }
}
