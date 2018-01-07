package nju.lighting.presentation.promotionui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import nju.lighting.presentation.documentui.CommodityItem;
import nju.lighting.presentation.documentui.GiftListController;
import nju.lighting.presentation.utils.PromotionHelper;
import nju.lighting.vo.doc.giftdoc.GiftItemVO;
import nju.lighting.vo.promotion.PromotionVO;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Created on 2017/12/7.
 * Description
 * @author 陈俊宇
 */
public class Combo implements Initializable {
    @FXML
    Label off;
    @FXML
    VBox combo;
    private PromotionVO promotion;

    public Combo() {
        promotion = Promotion.promotion;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        off.setText("" + promotion.getOff());

        List<GiftItemVO> giftItemListVO = promotion.getGoods();
        combo.getChildren().add(PromotionHelper.loadGiftList(giftItemListVO.stream()
                .map(x -> new CommodityItem(x))
                .collect(Collectors.toList())));

    }
}
