package nju.lighting.presentation.promotionui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import nju.lighting.presentation.utils.DateHelper;
import nju.lighting.vo.promotion.PromotionVO;
import shared.PromotionType;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created on 2017/12/5.
 * Description
 * @author 陈俊宇
 */
public class Promotion {

    static PromotionVO promotion;
    static HashMap<PromotionType, String> hashMap = new HashMap<>();

    static {
        hashMap.put(PromotionType.PriceOriented, "priceOriented.fxml");
        hashMap.put(PromotionType.Combo, "Combo.fxml");
        hashMap.put(PromotionType.CustomerOriented, "CustomerOriented.fxml");
    }

    @FXML
    Label title, validTime, creator;
    @FXML
    VBox container;
    @FXML
    ScrollPane scrollpane;
    String validTimeStr;

    public void init(PromotionVO promotion) {
        this.promotion = promotion;
        validTimeStr = DateHelper.approximateTime(promotion.getStartDate()) + " - "
                + DateHelper.approximateTime(promotion.getEndDate());

        title.setText(promotion.getName());
        creator.setText(promotion.getCreator().getUsername());
        validTime.setText(validTimeStr);

        try {
            container.getChildren().add(FXMLLoader.load(getClass().getResource(hashMap.get(promotion.getType()))));

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
