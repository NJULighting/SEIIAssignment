package nju.lighting.po.promotion;


import shared.CustomerGrade;
import shared.PromotionType;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * Created on 2017/10/19.
 * Description:
 * @author 陈俊宇
 */
public class PromotionPO implements Serializable {
    private static final long serialVersionUID = 213143123109L;

    private int id;
    private String name;
    private String creatorId;
    private PromotionType type;
    private Date startDate;
    private Date endDate;
    private Date time;
    private CustomerGrade level;
    private double total; // Price target
    private List<PromotionPackageItemPO> goods;
    private double off;
    private double vouchers;
    private Date vouchersEndDate;

    public PromotionPO(int id, String creatorId, String name, PromotionType type, Date startDate, Date endDate, Date time, CustomerGrade level
            , double total, List<PromotionPackageItemPO> goods, double off, double vouchers, Date vouchersEndDate) {
        this.id = id;
        this.creatorId = creatorId;
        this.name = name;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.time = time;
        this.level = level;
        this.total = total;
        this.goods = goods;
        this.off = off;
        this.vouchers = vouchers;
        this.vouchersEndDate = vouchersEndDate;
    }

    /**
     * Constructor for bl to data
     */
    public PromotionPO(String name, String creatorId, PromotionType type, Date startDate, Date endDate, Date time, CustomerGrade level
            , double total, List<PromotionPackageItemPO> goods, double off, double vouchers, Date vouchersEndDate) {
        this.name = name;
        this.creatorId = creatorId;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.time = time;
        this.level = level;
        this.total = total;
        this.goods = goods;
        this.off = off;
        this.vouchers = vouchers;
        this.vouchersEndDate = vouchersEndDate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCreatorId() {
        return creatorId;
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

    public Date getTime() {
        return time;
    }

    public CustomerGrade getLevel() {
        return level;
    }

    public double getTotal() {
        return total;
    }

    public List<PromotionPackageItemPO> getGoods() {
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
}
