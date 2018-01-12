package nju.lighting.presentation.utils;

import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import nju.lighting.presentation.documentui.CommodityItem;
import nju.lighting.presentation.documentui.CommodityList;
import nju.lighting.presentation.documentui.GiftListController;
import nju.lighting.presentation.mainui.Upper;
import nju.lighting.presentation.promotionui.BenefitsPlan;
import nju.lighting.vo.promotion.PromotionVO;
import shared.PromotionType;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Created on 2017/12/27.
 * Description
 * @author 陈俊宇
 */
public class PromotionHelper {

    //选择促销策略，与选择商品/客户大致相同
    public static void setPromotion(Upper upper, SimpleObjectProperty<PromotionVO> promotionProperty, List<PromotionVO> list) {
        list.stream().forEach(x->
        System.out.println("PromotionLevel"+x.getLevel()));
        FXMLLoader loader = new FXMLLoader(CustomerHelper.class.getClassLoader().getResource("nju/lighting/presentation/promotionui/BenefitsPlan.fxml"));
        try {
            upper.setChildren(loader.load(), ">选择促销策略");
        } catch (IOException e) {
            e.printStackTrace();
        }

        BenefitsPlan controller = loader.getController();
        controller.init(list, upper, promotionProperty);


    }

    public static Node loadGiftList(List<CommodityItem> commodityList) {
        Node res = null;
        try {
            FXMLLoader loader = new FXMLLoader(PromotionHelper.class.getClassLoader().getResource("nju/lighting/presentation/documentui/giftList.fxml"));
            res = loader.load();
            GiftListController controller = loader.getController();
            controller.getGiftObservableList().setAll(commodityList);



        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }
}

