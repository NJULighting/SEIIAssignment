package nju.lighting.vo.doc.giftdoc;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import nju.lighting.po.doc.giftdoc.GiftItemPO;
import nju.lighting.vo.commodity.BasicCommodityItemVO;

/**
 * Created on 2017/10/22.
 * Description
 * @author 陈俊宇
 */
public class GiftItemVO {
    private int id;
    private BasicCommodityItemVO commodity;
    private int count;
    private double subtotal;

    /**
     * Constructor for promotion
     */
    public GiftItemVO(BasicCommodityItemVO commodity, int count) {
        this.commodity = commodity;
        this.count = count;
        this.subtotal = commodity.getRecentSellPrice() * count;
    }

    /**
     * Constructor for bl
     */
    public GiftItemVO(int id, BasicCommodityItemVO commodity, int count) {
        this.id = id;
        this.commodity = commodity;
        this.count = count;
        this.subtotal = commodity.getRecentSellPrice() * count;
    }

    public BasicCommodityItemVO getCommodity() {
        return commodity;
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

    public double getPrice() {
        return commodity.getRecentSellPrice();
    }

    public int getId() {
        return id;
    }

    public ObservableValue<String> commodityNameProperty() {
        return new SimpleStringProperty(commodity.getName());
    }

    public ObservableValue<String> countProperty() {
        return new SimpleStringProperty("" + count);
    }

    public ObservableValue<String> subtotalProperty() {
        return new SimpleStringProperty("" + subtotal);
    }

    public ObservableValue<String> priceProperty() {
        return new SimpleStringProperty("" + commodity.getRecentSellPrice());
    }

    GiftItemPO toPO() {
        return new GiftItemPO(commodity.getId(), count, subtotal);
    }
}
