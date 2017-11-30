package nju.lighting.po.promotion;

import javax.persistence.*;

/**
 * Created on 2017/11/27.
 * Description:
 *
 * @author iznauy
 */
@Entity
@Table(name = "PROMOTION_COMMODITY")
public class PromotionPackageItemPO {

    private String commodityId;

    private int id;

    private int promotionId;

    public PromotionPackageItemPO() {

    }

    public PromotionPackageItemPO(String commodityId, int id, int promotionId) {
        this.commodityId = commodityId;
        this.id = id;
        this.promotionId = promotionId;
    }

    public PromotionPackageItemPO(String commodityId, int promotionId) {
        this.commodityId = commodityId;
        this.promotionId = promotionId;
    }



    @Override
    public String toString() {
        return "PromotionPackageItemPO{" +
                "commodityId='" + commodityId + '\'' +
                ", id=" + id +
                ", promotionId=" + promotionId +
                '}';
    }

    public PromotionPackageItemPO(String commodityId) {
        this.commodityId = commodityId;
    }

    @Column(name = "GOOD_ID", nullable = false, length = 36)
    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "PROMOTION_ID", nullable = false)
    public int getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(int promotionId) {
        this.promotionId = promotionId;
    }
}
