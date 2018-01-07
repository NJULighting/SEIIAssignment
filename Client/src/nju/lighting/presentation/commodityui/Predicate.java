package nju.lighting.presentation.commodityui;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import nju.lighting.vo.commodity.CommodityItemVO;

/**
 * Created on 2018/1/1.
 * Description
 * @author 陈俊宇
 */
public class Predicate {

    @FXML
    HBox imageContainer;

    void init(CommodityItemVO commodityItemVO) {

        imageContainer.getChildren().add(new ImageView("file:C:\\Users\\liao\\Desktop\\python\\analysis\\image\\" + commodityItemVO.getId() + ".jpg"));


    }
}
