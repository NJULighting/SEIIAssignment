package nju.lighting.presentation.documentui;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import nju.lighting.vo.doc.giftdoc.GiftItemVO;

/**
 * Created on 2017/12/10.
 * Description
 *
 * @author 陈俊宇
 */
public class CommodityItem {
    SimpleDoubleProperty price;
    SimpleIntegerProperty count;
    SimpleStringProperty name;
    SimpleDoubleProperty subtotal;
    SimpleBooleanProperty gift;
    GiftItemVO giftItem;

    public CommodityItem(GiftItemVO vo){
        init(vo);
    }

    public CommodityItem(GiftItemVO vo,boolean gift){
      init(vo);
      this.gift.setValue(gift);

    }
    void init(GiftItemVO vo){
        giftItem=vo;
        price = new SimpleDoubleProperty(vo.getPrice());
        count = new SimpleIntegerProperty(vo.getCount());
        name =new SimpleStringProperty(vo.getCommodity().getName());
        subtotal =new SimpleDoubleProperty(vo.getSubtotal());
        gift =new SimpleBooleanProperty(false);
    }

    public boolean isGift() {
        return gift.get();
    }

    public SimpleBooleanProperty giftProperty() {
        return gift;
    }

    public double getPrice() {
        return price.get();
    }



    public SimpleDoubleProperty subtotalProperty() {
        return subtotal;
    }

    public SimpleDoubleProperty priceProperty() {
        return price;
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public int getCount() {
        return count.get();
    }

    public SimpleIntegerProperty countProperty() {
        return count;
    }

    public void setCount(int count) {
        this.count.set(count);
    }

    public String getName() {
        return name.get();
    }

    public void setSubtotal(double subtotal) {
        this.subtotal.set(subtotal);
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setGift(boolean gift) {
        this.gift.set(gift);
    }

    public GiftItemVO getGiftItem() {
        return giftItem;
    }
}
