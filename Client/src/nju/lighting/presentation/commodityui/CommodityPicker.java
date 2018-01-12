package nju.lighting.presentation.commodityui;

import com.jfoenix.controls.JFXButton;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import nju.lighting.presentation.mainui.Upper;
import nju.lighting.vo.commodity.BasicCommodityItemVO;

import java.io.IOException;

/**
 * Created on 2017/12/17.
 * Description 商品选择界面的控制类
 * @author 陈俊宇
 */
public class CommodityPicker {

    private CommodityCategory category;

    private Upper upper;


    @FXML
    VBox container;

    @FXML
    JFXButton okBtn;


    public void init(Upper upper, ObservableList<BasicCommodityItemVO> commodityList) {
        init(upper);
        okBtn.setOnAction(e -> {
            if (category.getSelectedCommodities().size() != 0) {
                commodityList.addAll(category.getSelectedCommodities());
                upper.back();
            }
        });
    }

    public void init(Upper upper, SimpleObjectProperty<BasicCommodityItemVO> commodity) {
        init(upper);
        category.setSingle();
        okBtn.setOnAction(e -> {
            if (category.getSelectedCommodities().size() != 0) {
                commodity.set(category.getSelectedCommodities().get(0));
                upper.back();
            }
        });
    }

    private void init(Upper upper) {
        this.upper = upper;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("CommodityCategory.fxml"));
        try {
            container.getChildren().add(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        category = loader.getController();
    }


}
