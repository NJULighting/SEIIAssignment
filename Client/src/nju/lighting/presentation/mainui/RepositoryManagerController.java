package nju.lighting.presentation.mainui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created on 2017/11/26.
 * Description  库存管理人员的功能栏控制类
 * @author 陈俊宇
 */
public class RepositoryManagerController extends MainUIController {
    @FXML
    Button commodityBtn, historyBtn, repositoryViewBtn, repositoryCountingBtn, lossAndGainDoc, alertDoc,giftDoc;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buttons = new Button[]{historyBtn, commodityBtn, repositoryViewBtn, repositoryCountingBtn, lossAndGainDoc, alertDoc,giftDoc};
        urls = new String[]{"nju/lighting/presentation/homeui/HomePage.fxml", "nju/lighting/presentation/commodityui/CommodityManage.fxml",
                "nju/lighting/presentation/repositoryui/RepositoryView.fxml", "nju/lighting/presentation/repositoryui/RepositoryCounting.fxml",
                "nju/lighting/presentation/documentui/lossandgaindoc/AddLossAndGainDoc.fxml", "nju/lighting/presentation/documentui/alertdoc/AddAlertDoc.fxml",
                "nju/lighting/presentation/documentui/giftdoc/AddGiftDoc.fxml"};
        MAIN_BUTTON_SIZE = urls.length;
        super.initialize(location, resources);
    }
}
