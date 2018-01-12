package nju.lighting.presentation.utils;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import nju.lighting.presentation.commodityui.CommodityPicker;
import nju.lighting.presentation.documentui.CommodityItem;
import nju.lighting.presentation.mainui.Upper;
import nju.lighting.vo.commodity.BasicCommodityItemVO;

import java.io.IOException;
import java.util.stream.Collectors;

/**
 * Created on 2017/12/17.
 * Description
 * @author 陈俊宇
 */
public class CommodityHelper {

    public static void chooseCommodity(Upper upper, ObservableList<BasicCommodityItemVO> commodities) {
        getPicker(upper).init(upper, commodities);
    }

    public static void setCommodity(Upper upper, SimpleObjectProperty<BasicCommodityItemVO> commodity) {
        getPicker(upper).init(upper, commodity);
    }

    private static CommodityPicker getPicker(Upper upper) {
        FXMLLoader loader = new FXMLLoader(CommodityHelper.class.getClassLoader().getResource("nju/lighting/presentation/commodityui/CommodityPicker.fxml"));
        try {
            upper.setChildren(loader.load(), ">选择商品");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loader.getController();
    }

    public static ListChangeListener<BasicCommodityItemVO> getListChangeListener(
            ObservableList<CommodityItem> docItemList, boolean hasMax){
        return new ListChangeListener<BasicCommodityItemVO>() {
            @Override
            public void onChanged(Change<? extends BasicCommodityItemVO> c) {
                while (c.next()) {
                    docItemList.addAll(c.getAddedSubList().stream()
                            .filter(x -> (docItemList.stream()
                                    .filter(y -> y.getCommodity().getId().equals(x.getId()))
                                    .count()) == 0)
                            .map(x -> new CommodityItem(x, 1, hasMax))
                            .collect(Collectors.toList()));
                }
            }
        };
    }

    public static ListChangeListener<BasicCommodityItemVO> getListChangeListener(
            ObservableList<CommodityItem> docItemList){
        return getListChangeListener(docItemList,false);
    }

}
