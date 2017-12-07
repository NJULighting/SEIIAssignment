package nju.lighting.presentation.promotionui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import nju.lighting.presentation.documentui.GiftListController;
import nju.lighting.presentation.utils.DateHelper;
import nju.lighting.vo.doc.giftdoc.GiftItemListVO;
import nju.lighting.vo.promotion.PromotionVO;

import java.io.IOException;
import java.net.URL;
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
    Label title;

    @FXML
    Label off;

    @FXML
    Label creator;

    @FXML
    Label validTime;

    @FXML
    Label voucher;

    @FXML
    Label voucherEndTime,noneGift;

    @FXML
    VBox combo,gift;


    public PriceOriented(){

        promotion= PromotionManageController.selectedPromotion;
        validTimeStr= DateHelper.toString(promotion.getStartDate())+" - "
                + DateHelper.toString(promotion.getEndDate());

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        title.setText(promotion.getName());
        off.setText(""+promotion.getOff());
        creator.setText(promotion.getCreator().getUsername());
        validTime.setText(validTimeStr);
        voucher.setText(""+promotion.getVouchers());
        voucherEndTime.setText(DateHelper.toString(promotion.getVouchersEndDate()));
        System.out.println(promotion);
        if (promotion.getGoods()!=null){
            GiftItemListVO giftItemListVO = new GiftItemListVO(promotion.getGoods());
            GiftListController.setGiftItemListVO(giftItemListVO);
            try {

                gift.getChildren().add(FXMLLoader.load(getClass().getResource("../documentui/GiftList.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else
            noneGift.setVisible(true);



    }
}
