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
 * Description 和商品有关的tableView 的值
 *
 * @author 陈俊宇
 */
public class CommodityItem {
    private SimpleDoubleProperty price = new SimpleDoubleProperty();
    private SimpleIntegerProperty count = new SimpleIntegerProperty();
    private SimpleStringProperty name = new SimpleStringProperty();
    private SimpleDoubleProperty subtotal = new SimpleDoubleProperty();
    private SimpleBooleanProperty hasMax = new SimpleBooleanProperty(false);
    private SimpleStringProperty comments = new SimpleStringProperty();
    private SimpleStringProperty modelNum = new SimpleStringProperty();
    private SimpleStringProperty id = new SimpleStringProperty();
    private BasicCommodityItemVO commodity;


    //创建时只需要商品和数量
    public CommodityItem(BasicCommodityItemVO commodity, int count) {
        this.count.set(count);
        init(commodity);
    }

    public CommodityItem(BasicCommodityItemVO commodity, int count, boolean hasMax) {
        this.count.set(count);
        this.hasMax.setValue(hasMax);
        init(commodity);

    }

    //查看单据时由SalesDocItem 或GiftDocItem 构造
    public CommodityItem(SalesDocItemVO vo) {
        comments.set(vo.getRemarks());
        count.set(vo.getNumber());
        init(vo.getCommodity());
        price.set(vo.getTotalAmount()/getCount());
    }
    public CommodityItem(SalesDocItemVO vo,boolean hasMax) {
        comments.set(vo.getRemarks());
        count.set(vo.getNumber());
        this.hasMax.setValue(hasMax);
        init(vo.getCommodity());
        price.set(vo.getTotalAmount()/getCount());
    }

    public CommodityItem(StockDocItemVO vo) {
        comments.set(vo.getRemarks());
        count.set(vo.getNumber());
        init(vo.getCommodity());
        price.set(vo.getTotalAmount()/getCount());
    }

    public CommodityItem(GiftItemVO vo, boolean gift) {
        count.set(vo.getCount());
        init(vo.getCommodity());
        price.set(vo.getPrice());


    }

    public CommodityItem(GiftItemVO vo) {
        count.set(vo.getCount());
        init(vo.getCommodity());
        price.set(vo.getPrice());
        hasMax.setValue(true);
    }

    private void init(BasicCommodityItemVO commodity) {
        this.commodity=commodity;
        price.set(commodity.getRecentSellPrice());
        name.set(commodity.getName());
        id.set(commodity.getId());
        modelNum.set(commodity.getModelNumber());
        subtotal.bind(price.multiply(count));
    }

    public SalesDocItemVO toSalesDocItem() {
        return new SalesDocItemVO(getCount(), getComments(), getCommodity(), getPrice());
    }

    public StockDocItemVO toStockDocItem() {
        return new StockDocItemVO(commodity, getCount(), getComments(),getPrice());
    }

    public GiftItemVO toGiftItem() {
        return new GiftItemVO(commodity, getCount());
    }


    public double getPrice() {
        return price.get();
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    SimpleDoubleProperty subtotalProperty() {
        return subtotal;
    }

    SimpleDoubleProperty priceProperty() {
        return price;
    }

    public int getCount() {
        return count.get();
    }

    public void setCount(int count) {
        this.count.set(count);
    }

    SimpleIntegerProperty countProperty() {
        return count;
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

    public String getComments() {
        return comments.get();
    }

    public void setComments(String comments) {
        this.comments.set(comments);
    }

    SimpleStringProperty commentsProperty() {
        return comments;
    }

    public String getModelNum() {
        return modelNum.get();
    }

    SimpleStringProperty modelNumProperty() {
        return modelNum;
    }

    public String getId() {
        return id.get();
    }

    SimpleStringProperty idProperty() {
        return id;
    }

    public BasicCommodityItemVO getCommodity() {
        return commodity;
    }

    boolean isHasMax() {
        return hasMax.get();
    }

    public SimpleBooleanProperty hasMaxProperty() {
        return hasMax;
    }

    public double getSubtotal() {
        return subtotal.get();
    }


}
