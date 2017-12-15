package nju.lighting.presentation.commodityui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import nju.lighting.vo.commodity.CommodityItemVO;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * Created on 2017/12/14.
 * Description
 *
 * @author 陈俊宇
 */
public class CommodityReadOnly  implements Initializable{
    private static CommodityItemVO commodity;


    @FXML
    Label id,name,modelNumber,repCount,inPrice,sellPrice,recentInPrice,recentSellPrice;

    public static void setCommodity(CommodityItemVO commodity) {
        CommodityReadOnly.commodity = commodity;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        id.setText(commodity.getId());
        name.setText(commodity.getName());
        modelNumber.setText(commodity.getModelNumber());
        repCount.setText(commodity.getRepCount()+"");
        inPrice.setText(commodity.getInPrice()+"");
        sellPrice.setText(commodity.getSellPrice()+"");
        recentInPrice.setText(commodity.getRecentInPrice()+"");
        recentSellPrice.setText(commodity.getRecentSellPrice()+"");
    }
}
