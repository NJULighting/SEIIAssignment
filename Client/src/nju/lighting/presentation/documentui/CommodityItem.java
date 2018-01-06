package nju.lighting.presentation.documentui;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import nju.lighting.vo.commodity.BasicCommodityItemVO;
import nju.lighting.vo.doc.giftdoc.GiftItemVO;
import nju.lighting.vo.doc.salesdoc.SalesDocItemVO;
import nju.lighting.vo.doc.stockdoc.StockDocItemVO;

/**
 * Created on 2017/12/10.
 * Description
 * @author 陈俊宇
 */
public class CommodityItem {
    SimpleDoubleProperty price = new SimpleDoubleProperty();
    SimpleIntegerProperty count = new SimpleIntegerProperty();
    SimpleStringProperty name = new SimpleStringProperty();
    SimpleDoubleProperty subtotal = new SimpleDoubleProperty();
    SimpleBooleanProperty gift = new SimpleBooleanProperty(false);
    SimpleStringProperty comments = new SimpleStringProperty();
    SimpleStringProperty modelNum = new SimpleStringProperty();
    SimpleStringProperty id = new SimpleStringProperty();
    BasicCommodityItemVO commodity;


    //创建时只需要商品和数量
    public CommodityItem(BasicCommodityItemVO commodity, int count) {
        this.commodity = commodity;
        this.count.set(count);
        init(commodity);
    }

    //查看单据时由SalesDocItem 或GiftDocItem 构造
    public CommodityItem(SalesDocItemVO vo) {
        comments.set(vo.getRemarks());
        count.set(vo.getNumber());
        init(vo.getCommodity());
    }

    public CommodityItem(StockDocItemVO vo){
        comments.set(vo.getRemarks());
        count.set(vo.getNumber());
        init(vo.getCommodity());
    }

    public CommodityItem(GiftItemVO vo, boolean gift) {
        count.set(vo.getCount());
        init(vo.getCommodity());
        price.set(vo.getPrice());
        this.gift.setValue(gift);

    }

    public CommodityItem(GiftItemVO vo) {
        count.set(vo.getCount());
        init(vo.getCommodity());
        price.set(vo.getPrice());
    }

    void init(BasicCommodityItemVO commodity) {
        price.set(commodity.getRecentSellPrice());
        name.set(commodity.getName());
        id.set(commodity.getId());
        modelNum.set(commodity.getModelNumber());
        subtotal.bind(price.multiply(count));
    }

    public SalesDocItemVO toSalesDocItem(){
        return new SalesDocItemVO(getCount(),getComments(),getCommodity(),getPrice());
    }

    public boolean isGift() {
        return gift.get();
    }

    public void setGift(boolean gift) {
        this.gift.set(gift);
    }

    public SimpleBooleanProperty giftProperty() {
        return gift;
    }

    public double getPrice() {
        return price.get();
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public SimpleDoubleProperty subtotalProperty() {
        return subtotal;
    }

    public SimpleDoubleProperty priceProperty() {
        return price;
    }

    public int getCount() {
        return count.get();
    }

    public void setCount(int count) {
        this.count.set(count);
    }

    public SimpleIntegerProperty countProperty() {
        return count;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public String getComments() {
        return comments.get();
    }

    public void setComments(String comments) {
        this.comments.set(comments);
    }

    public SimpleStringProperty commentsProperty() {
        return comments;
    }

    public String getModelNum() {
        return modelNum.get();
    }

    public SimpleStringProperty modelNumProperty() {
        return modelNum;
    }

    public String getId() {
        return id.get();
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public BasicCommodityItemVO getCommodity() {
        return commodity;
    }
}
