package shared;

import nju.lighting.bl.utils.CollectionTransformer;
import nju.lighting.po.promotion.PromotionPO;
import nju.lighting.po.promotion.PromotionPackageItemPO;
import nju.lighting.vo.UserVO;
import nju.lighting.vo.doc.giftdoc.GiftItemVO;
import nju.lighting.vo.promotion.PromotionVO;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created on 2017/12/7.
 * Description:
 * @author Liao
 */
public class PromotionBuildInfo {
    private final String name;
    private final String creatorId;
    private final PromotionType type;
    private final Date startDate;
    private final Date endDate;
    private final Date createTime;
    private final CustomerGrade level;
    private final double price; // Price target
    private final List<GiftItemVO> goods;
    private final double off;
    private final double vouchers;
    private final Date vouchersEndDate;

    private PromotionBuildInfo(Builder builder) {
        name = builder.name;
        type = builder.type;
        startDate = builder.startDate;
        endDate = builder.endDate;
        createTime = new Date();
        level = Optional.ofNullable(builder.level).orElse(CustomerGrade.ONE);
        price = builder.price;
        goods = builder.goods;
        off = builder.off;
        vouchers = builder.vouchers;
        vouchersEndDate = Optional.ofNullable(builder.vouchersEndDate).orElse(new Date());
        creatorId = builder.userId;
    }

    public static class Builder {
        private String name;
        private PromotionType type;
        private Date startDate;
        private Date endDate;
        private CustomerGrade level;
        private double price;
        private List<GiftItemVO> goods;
        private double off;
        private double vouchers;
        private Date vouchersEndDate;
        private String userId;

        /**
         * This constructor should be used by presentation level
         * @param name name of promotion
         * @param type type of promotion
         * @param startDate start time of promotion
         * @param endDate end time of promotion
         * @param user creator of this promotion strategy
         */
        public Builder(String name, PromotionType type, Date startDate, Date endDate, UserVO user) {
            this.name = name;
            this.type = type;
            this.startDate = startDate;
            this.endDate = endDate;
            userId = user.getID();
        }

        public Builder level(CustomerGrade level) {
            this.level = level;
            return this;
        }

        public Builder price(double price) {
            this.price = price;
            return this;
        }

        public Builder goods(List<GiftItemVO> goods) {
            this.goods = goods;
            return this;
        }

        public Builder off(double off) {
            this.off = off;
            return this;
        }

        public Builder vouchers(double vouchers, Date vouchersEndDate) {
            this.vouchers = vouchers;
            this.vouchersEndDate= vouchersEndDate;
            return this;
        }

        public PromotionBuildInfo build() {
            return new PromotionBuildInfo(this);
        }
    }

    public Date getCreateTime() {
        return createTime;
    }

    public String getName() {
        return name;
    }

    public PromotionType getType() {
        return type;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public CustomerGrade getLevel() {
        return level;
    }

    public double getPrice() {
        return price;
    }

    public List<GiftItemVO> getGoods() {
        return goods;
    }

    public double getOff() {
        return off;
    }

    public double getVouchers() {
        return vouchers;
    }

    public Date getVouchersEndDate() {
        return vouchersEndDate;
    }

    public String getCreatorId() {
        return creatorId;
    }
}
