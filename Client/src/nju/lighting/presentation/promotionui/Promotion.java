package nju.lighting.presentation.promotionui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import nju.lighting.vo.promotion.PriceOrientedVO;
import nju.lighting.vo.promotion.PromotionVO;
import shared.PromotionType;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created on 2017/12/5.
 * Description
 *
 * @author 陈俊宇
 */
public class Promotion {
    static HashMap<PromotionType,String > hashMap =new HashMap<>();

    static {
        hashMap.put(PromotionType.PriceOriented,"priceOriented.fxml");
        hashMap.put(PromotionType.Combo,"comboPromotion.fxml");
        hashMap.put(PromotionType.CustomerOriented,"customerOrientedPromotion.fxml");
    }

    public Promotion(PromotionVO promotionVO) throws IOException {
        String url=hashMap.get(promotionVO.getType());

        Stage stage=new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource(url))));
        stage.show();
    }
}
