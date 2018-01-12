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
 *
 * @author 陈俊宇
 */
public class CommodityHelper {

    /**
     * 调用者需要选择商品时 按钮所注册的方法
     *
     * @param upper       调用者的控制类
     * @param commodities 调用者的ObservableList，可以通过对他增删而触发调用者的一系列操作
     */
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

    /**
     * 选择商品后，调用者需要做的一系列的事情
     *
     * @param docItemList 调用者需要变动的列表
     * @param hasMax      调用者小项（全为商品项）的商品数量属性是否会有库存限制
     * @return 调用者 需要注册的listener
     */
    public static ListChangeListener<BasicCommodityItemVO> getListChangeListener(
            ObservableList<CommodityItem> docItemList, boolean hasMax) {
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
            ObservableList<CommodityItem> docItemList) {
        return getListChangeListener(docItemList, false);
    }

}
