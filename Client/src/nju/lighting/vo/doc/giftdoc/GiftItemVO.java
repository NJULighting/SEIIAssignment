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

    /*
    构造赠品项的时候所用的构造函数，只需要传入商品和数量，根据实时的结果得到价格
     */
    public GiftItemVO(BasicCommodityItemVO commodity, int count) {
        this.commodity = commodity;
        this.count = count;
        this.subtotal=commodity.getRecentSellPrice()*count;
    }


    /*
    显示的时候所需要的构造函数，因为商品的结果可能发生改变，所以商品的价格应该取自于
    数据库中存在的数据
     */
    public GiftItemVO(BasicCommodityItemVO commodity, int count, double subtotal) {
        this.commodity = commodity;
        this.count = count;
        this.subtotal = subtotal;
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
