package nju.lighting.presentation.promotionui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
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
 * Created on 2017/12/7.
 * Description 查看针对客户的促销策略的控制类
 * @author 陈俊宇
 */
public class CustomerOriented implements Initializable {
    @FXML
    Label off, voucher, voucherEndTime, trigger;
    @FXML
    VBox container, giftBox, voucherBox;
    @FXML
    HBox offBox;
    private PromotionVO promotion;


    public CustomerOriented() {
        promotion = Promotion.promotion;
    }

    public void initialize(URL location, ResourceBundle resources) {

        trigger.setText("当用户等级大于" + promotion.getLevel() + "时");
        if (promotion.getVouchers() == 0)
            container.getChildren().remove(voucherBox);
        else {
            voucher.setText("" + promotion.getVouchers());
            voucherEndTime.setText(DateHelper.approximateTime(promotion.getVouchersEndDate()));
        }

        if (promotion.getOff() == 0)
            container.getChildren().remove(offBox);
        else
            off.setText(promotion.getOff() + "");
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
