package nju.lightingServer.po.doc.giftdoc;

/**
 * Created on 2017/10/22.
 * Description
 * @author 陈俊宇
 */
public class GiftItemPO {
    private String commodityID;

    private int count;

    private double subtotal;


    public GiftItemPO(String commodity, int count) {
        this.commodityID = commodity;
        this.count = count;
    }

    public String getCommodity() {
        return commodityID;
    }

    public void setCommodityID(String commodity) {
        this.commodityID = commodity;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
}
