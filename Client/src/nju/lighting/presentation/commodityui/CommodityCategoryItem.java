package nju.lighting.presentation.commodityui;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import nju.lighting.vo.commodity.CommodityItemVO;
import nju.lighting.vo.commodity.Nameable;

/**
 * Created on 2017/12/25.
 * Description
 * 商品管理界面树表视图所需要的value
 *
 * @author 陈俊宇
 */
public class CommodityCategoryItem {
    private SimpleStringProperty name = new SimpleStringProperty();
    private SimpleStringProperty id = new SimpleStringProperty("");
    private SimpleStringProperty modelNumber = new SimpleStringProperty();
    private SimpleIntegerProperty repCount = new SimpleIntegerProperty();
    private SimpleDoubleProperty inPrice = new SimpleDoubleProperty();
    private SimpleDoubleProperty sellPrice = new SimpleDoubleProperty();
    private SimpleDoubleProperty recentInPrice = new SimpleDoubleProperty();
    private SimpleDoubleProperty recentSellPrice = new SimpleDoubleProperty();
    private Nameable item;


    public CommodityCategoryItem(Nameable item) {
        this.item = item;
        name.set(item.getName());
        if (item.getClass().equals(CommodityItemVO.class)) {
            CommodityItemVO vo = (CommodityItemVO) item;
            id.set(vo.getId());
            modelNumber.set(vo.getModelNumber());
            repCount.set(vo.getRepCount());
            inPrice.set(vo.getInPrice());
            recentInPrice.set(vo.getRecentInPrice());
            sellPrice.set(vo.getSellPrice());
            recentSellPrice.set(vo.getRecentSellPrice());
        }
    }


    public CommodityCategoryItem(String name) {
        this.name.set(name);
        item = new Nameable() {
            @Override
            public String getName() {
                return name;
            }
        };
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    SimpleStringProperty nameProperty() {
        return name;
    }

    public String getId() {
        return id.get();
    }

    public void setId(String id) {
        this.id.set(id);
    }

    SimpleStringProperty idProperty() {
        return id;
    }

    public String getModelNumber() {
        return modelNumber.get();
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber.set(modelNumber);
    }

    SimpleStringProperty modelNumberProperty() {
        return modelNumber;
    }

    public int getRepCount() {
        return repCount.get();
    }

    public void setRepCount(int repCount) {
        this.repCount.set(repCount);
    }

    SimpleIntegerProperty repCountProperty() {
        return repCount;
    }

    public double getInPrice() {
        return inPrice.get();
    }

    public void setInPrice(double inPrice) {
        this.inPrice.set(inPrice);
    }

    SimpleDoubleProperty inPriceProperty() {
        return inPrice;
    }

    public double getSellPrice() {
        return sellPrice.get();
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice.set(sellPrice);
    }

    SimpleDoubleProperty sellPriceProperty() {
        return sellPrice;
    }

    public double getRecentInPrice() {
        return recentInPrice.get();
    }

    public void setRecentInPrice(double recentInPrice) {
        this.recentInPrice.set(recentInPrice);
    }

    SimpleDoubleProperty recentInPriceProperty() {
        return recentInPrice;
    }

    public double getRecentSellPrice() {
        return recentSellPrice.get();
    }

    public void setRecentSellPrice(double recentSellPrice) {
        this.recentSellPrice.set(recentSellPrice);
    }

    SimpleDoubleProperty recentSellPriceProperty() {
        return recentSellPrice;
    }

    public Nameable getItem() {
        return item;
    }

    public void setItem(Nameable item) {
        this.item = item;
    }
}
