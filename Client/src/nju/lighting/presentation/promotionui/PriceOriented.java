package nju.lighting.presentation.promotionui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import nju.lighting.presentation.documentui.CommodityItem;
import nju.lighting.presentation.utils.DateHelper;
import nju.lighting.presentation.utils.PromotionHelper;
import nju.lighting.vo.doc.giftdoc.GiftItemVO;
import nju.lighting.vo.promotion.PromotionVO;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Created on 2017/12/4.
 * Description
 * @author 陈俊宇
 */
public class PriceOriented implements Initializable {
    @FXML
    Label trigger, voucher, voucherEndTime;
    @FXML
    VBox container, giftBox, voucherBox;
    private PromotionVO promotion;


    public PriceOriented() {

        promotion = Promotion.promotion;

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        trigger.setText("当总价大于" + promotion.getPrice() + "时");
        if (promotion.getVouchers() == 0)
            container.getChildren().remove(voucherBox);
        else {
            voucher.setText("" + promotion.getVouchers());
            voucherEndTime.setText(DateHelper.approximateTime(promotion.getVouchersEndDate()));
        }


        if (promotion.getGoods() == null)
            container.getChildren().remove(giftBox);
        else {
            List<GiftItemVO> giftItemListVO = promotion.getGoods();
            giftBox.getChildren().add(PromotionHelper.loadGiftList(giftItemListVO.stream()
                    .map(x -> new CommodityItem(x))
                    .collect(Collectors.toList())));
        }


    }
}
