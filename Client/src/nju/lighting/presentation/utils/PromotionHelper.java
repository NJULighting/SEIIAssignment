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

    static HashMap<PromotionType, String> typeStringHashMap = new HashMap<>();

    static {
        typeStringHashMap.put(PromotionType.CustomerOriented, "针对客户");
        typeStringHashMap.put(PromotionType.Combo, "组合商品降价");
        typeStringHashMap.put(PromotionType.PriceOriented, "针对总价");
    }

    public static String typeToString(PromotionType type) {
        return typeStringHashMap.get(type);
    }

    public static void setPromotion(Upper upper, SimpleObjectProperty<PromotionVO> promotionProperty, List<PromotionVO> list) {
        list.stream().forEach(x->
        System.out.println("PromotionLevel"+x.getLevel()));
        FXMLLoader loader = new FXMLLoader(CustomerHelper.class.getResource("../promotionui/BenefitsPlan.fxml"));
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
            FXMLLoader loader = new FXMLLoader(PromotionHelper.class.getResource("../documentui/giftList.fxml"));
            res = loader.load();
            GiftListController controller = loader.getController();
            controller.getGiftObservableList().setAll(commodityList);



        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }
}

