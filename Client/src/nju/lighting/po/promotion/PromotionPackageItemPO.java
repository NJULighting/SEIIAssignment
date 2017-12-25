package nju.lighting.po.promotion;


import java.io.Serializable;

/**
 * Created on 2017/11/27.
 * Description:
 * @author iznauy
 */
public class PromotionPackageItemPO implements Serializable {
    private static final long serialVersionUID = 2873143123109L;
    private String commodityId;
    private int id;
    private int promotionId;

    private int commodityCount = 1;

    public PromotionPackageItemPO(String commodityId, int promotionId, int count) {
        this.commodityId = commodityId;
        this.promotionId = promotionId;
    }

    public PromotionPackageItemPO(int count, String commodityId) {
        this.commodityCount = count;
        this.commodityId = commodityId;
    }

    public String getCommodityId() {
        return commodityId;
    }

    public int getId() {
        return id;
    }

    public int getPromotionId() {
        return promotionId;
    }

    public int getCommodityCount() {
        return commodityCount;
    }
}
