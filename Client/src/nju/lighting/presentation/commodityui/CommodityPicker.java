package nju.lighting.presentation.commodityui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import nju.lighting.presentation.mainui.Upper;
import nju.lighting.vo.commodity.BasicCommodityItemVO;
import shared.CustomerType;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2017/12/17.
 * Description
 *
 * @author 陈俊宇
 */
public class CommodityPicker {

    List<BasicCommodityItemVO> commodities = new ArrayList<>();
    CommodityCategory category;


    ObservableList<BasicCommodityItemVO> commodityList;
    CustomerType type;
    Upper upper;


    @FXML
    VBox container;


    public void init(Upper upper, ObservableList<BasicCommodityItemVO> commodityList) {
        this.upper = upper;
        this.commodityList = commodityList;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CommodityCategory.fxml"));
        try {
            container.getChildren().add(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        category = loader.getController();
    }



    public void addCommodity(List<BasicCommodityItemVO> commodity) {
        commodityList.addAll(commodity);
    }

    @FXML
    void ok() {

        addCommodity(category.getSelectedCommodities());
        upper.back();

    }




    public List<BasicCommodityItemVO> getCommodities() {
        return commodities;
    }

}
