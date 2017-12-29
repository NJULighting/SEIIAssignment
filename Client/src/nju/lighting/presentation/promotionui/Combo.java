package nju.lighting.presentation.promotionui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import nju.lighting.presentation.documentui.CommodityItem;
import nju.lighting.presentation.documentui.GiftListController;
import nju.lighting.presentation.utils.DateHelper;
import nju.lighting.presentation.utils.PromotionHelper;
import nju.lighting.vo.doc.giftdoc.GiftItemVO;
import nju.lighting.vo.promotion.PromotionVO;


import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Created on 2017/12/7.
 * Description
 *
 * @author 陈俊宇
 */
public class Combo implements Initializable {
    private PromotionVO promotion;


    @FXML
    Label off;


    @FXML
    VBox combo;

    public Combo() {
        promotion = Promotion.promotion;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        off.setText("" + promotion.getOff());

        List<GiftItemVO> giftItemListVO = promotion.getGoods();
        GiftListController.setGiftItemListVO(giftItemListVO);
        combo.getChildren().add(PromotionHelper.loadGiftList(giftItemListVO.stream()
        .map(x-> new CommodityItem(x))
        .collect(Collectors.toList())));

    }
}
