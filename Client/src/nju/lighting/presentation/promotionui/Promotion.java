package nju.lighting.presentation.promotionui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import nju.lighting.presentation.utils.DateHelper;
import nju.lighting.vo.promotion.PromotionVO;
import shared.PromotionType;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * Created on 2017/12/5.
 * Description
 *
 * @author 陈俊宇
 */
public class Promotion implements Initializable{

    static PromotionVO promotion;
    @FXML
    Label title,validTime,creator;

    @FXML
    VBox container;

    @FXML
    ScrollPane scrollpane;

    String validTimeStr;
    static HashMap<PromotionType,String > hashMap =new HashMap<>();

    static {
        hashMap.put(PromotionType.PriceOriented,"priceOriented.fxml");
        hashMap.put(PromotionType.Combo,"Combo.fxml");
        hashMap.put(PromotionType.CustomerOriented,"CustomerOriented.fxml");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

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

        Stage stage=new Stage();
        stage.setScene(new Scene(scrollpane));
        stage.show();
    }
}
