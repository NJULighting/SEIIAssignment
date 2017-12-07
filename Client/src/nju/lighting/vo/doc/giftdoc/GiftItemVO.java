package nju.lighting.vo.doc.giftdoc;


import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import nju.lighting.vo.commodity.BasicCommodityItemVO;

/**
 * Created on 2017/10/22.
 * Description
 * @author 陈俊宇
 */
public class GiftItemVO {

    private BasicCommodityItemVO commodity;

    private int count;

    private double subtotal;

    public GiftItemVO(BasicCommodityItemVO commodity, int count) {
        this.commodity = commodity;
        this.count = count;
        this.subtotal=commodity.getRecentSellPrice()*count;
    }

    public String getCommodityID() {
        return commodity.getId();
    }

    public double getSubtotal() {
        return subtotal;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ObservableValue<String> commodityNameProperty() {
       return new SimpleStringProperty(commodity.getName());
    }

    public ObservableValue<String> countProperty(){
        return new SimpleStringProperty(""+count);
    }

    public ObservableValue<String> subtotalProperty(){
        return new SimpleStringProperty(""+subtotal);
    }
    public ObservableValue<String> priceProperty(){
        return new SimpleStringProperty(""+commodity.getRecentSellPrice());
    }

}
