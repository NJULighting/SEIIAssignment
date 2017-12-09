package nju.lighting.presentation.promotionui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import nju.lighting.presentation.documentui.GiftListController;
import nju.lighting.presentation.utils.DateHelper;
import nju.lighting.vo.doc.giftdoc.GiftItemVO;
import nju.lighting.vo.promotion.PromotionVO;


import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created on 2017/12/7.
 * Description
 *
 * @author 陈俊宇
 */
public class Combo implements Initializable {
    private PromotionVO promotion;

    private String validTimeStr;
    @FXML
    Label title;

    @FXML
    Label off;

    @FXML
    Label creator;

    @FXML
    Label validTime;

    @FXML
    VBox combo;

    public Combo() {
        promotion = PromotionManageController.selectedPromotion;
        validTimeStr = DateHelper.approximateTime(promotion.getStartDate()) + " - "
                + DateHelper.approximateTime(promotion.getEndDate());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        title.setText(promotion.getName());
        off.setText("" + promotion.getOff());
        creator.setText(promotion.getCreator().getUsername());
        validTime.setText(validTimeStr);
        System.out.println(promotion);

        List<GiftItemVO> giftItemListVO = promotion.getGoods();
        GiftListController.setGiftItemListVO(giftItemListVO);
        try {
            combo.getChildren().add(FXMLLoader.load(getClass().getResource("../documentui/GiftList.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
