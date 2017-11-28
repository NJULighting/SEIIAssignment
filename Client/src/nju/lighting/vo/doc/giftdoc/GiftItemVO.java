package nju.lighting.vo.doc.giftdoc;


import javafx.beans.property.*;
import nju.lighting.vo.commodity.BasicCommodityItemVO;

/**
 * Created on 2017/10/22.
 * Description
 * @author 陈俊宇
 */
public class GiftItemVO {
    StringProperty commodityName;
    StringProperty count;
    StringProperty  subtotal;
    private BasicCommodityItemVO commodity;

//    private int count;


    public GiftItemVO(BasicCommodityItemVO commodity, int count) {
        this.commodityName=new SimpleStringProperty(commodity.getName());
        this.count=new SimpleStringProperty(""+count);
       // this.commodity = commodity;
//        this.count = count;
        this.subtotal=new SimpleStringProperty(""+commodity.getRecentSellPrice()*count);
    }

    public BasicCommodityItemVO getCommodity() {
        return commodity;
    }

//    public int getCount() {
//        return count;
//    }
//
//    public void setCount(int count) {
//        this.count = count;
//    }



    public String getCommodityName() {
        return commodityName.get();
    }

    public StringProperty commodityNameProperty() {
        return commodityName;
    }

    public String getCount() {
        return count.get();
    }

    public StringProperty countProperty() {
        return count;
    }

    public String getSubtotal() {
        return subtotal.get();
    }

    public StringProperty subtotalProperty() {
        return subtotal;
    }
}
