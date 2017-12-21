package nju.lighting.presentation.promotionui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import nju.lighting.presentation.commodityui.CommodityCategory;
import nju.lighting.presentation.documentui.GiftListController;
import nju.lighting.presentation.utils.DateHelper;

import nju.lighting.vo.doc.giftdoc.GiftItemVO;
import nju.lighting.vo.promotion.PromotionVO;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created on 2017/12/4.
 * Description
 *
 * @author 陈俊宇
 */
public class PriceOriented implements Initializable {
    private PromotionVO promotion;

    private String validTimeStr;

    @FXML
    Label trigger, voucher, voucherEndTime;

    @FXML
    VBox container,giftBox,voucherBox;



    public PriceOriented(){

        promotion = Promotion.promotion;

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        trigger.setText("当总价大于"+promotion.getPrice()+"时");
        if (promotion.getVouchers()==0)
            container.getChildren().remove(voucherBox);
        else {
            voucher.setText(""+promotion.getVouchers());
            voucherEndTime.setText(validTimeStr);
        }


        if (promotion.getGoods()==null)
            container.getChildren().remove(giftBox);
        else{
            List<GiftItemVO> giftItemListVO = promotion.getGoods();
            GiftListController.setGiftItemListVO(giftItemListVO);
            try {
                giftBox.getChildren().add(FXMLLoader.load(getClass().getResource("../documentui/GiftList.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
