package nju.lighting.presentation.utils;

import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import nju.lighting.presentation.mainui.Upper;
import nju.lighting.presentation.promotionui.BenefitsPlan;
import nju.lighting.vo.promotion.PromotionVO;

import java.io.IOException;
import java.util.List;

/**
 * Created on 2017/12/27.
 * Description
 *
 * @author 陈俊宇
 */
public class PromotionHelper {
    public static void setPromotion(Upper upper, SimpleObjectProperty<PromotionVO> promotionProperty, List<PromotionVO> list) {
        FXMLLoader loader = new FXMLLoader(CustomerHelper.class.getResource("../promotionui/BenefitsPlan.fxml"));
        try {
            upper.setChildren(loader.load(), "选择商品");
        } catch (IOException e) {
            e.printStackTrace();
        }

        BenefitsPlan controller = loader.getController();
        controller.init(list, upper, promotionProperty);


    }
}

