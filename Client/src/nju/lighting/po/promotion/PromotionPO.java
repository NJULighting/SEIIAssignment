package nju.lighting.po.promotion;


import shared.CustomerGrade;
import shared.PromotionType;

import java.io.Serializable;
import java.util.ArrayList;
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
    private String creatorID;
    private PromotionType type;
    private Date startDate;
    private Date endDate;
    private Date time;
    private CustomerGrade level;
    private double total;
    private List<PromotionPackageItemPO> goods;
    private double off;
    private double vouchers;
    private Date vouchersEndDate;

    public PromotionPO(int id, String creatorID, String name, PromotionType type, Date startDate, Date endDate, Date time, CustomerGrade level
            , double total, List<PromotionPackageItemPO> goods, double off, double vouchers, Date vouchersEndDate) {
        this.id = id;
        this.creatorID = creatorID;
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

    public PromotionPO(String name, String creatorID, PromotionType type, Date startDate, Date endDate, Date time, CustomerGrade level
            , double total, List<PromotionPackageItemPO> goods, double off, double vouchers, Date vouchersEndDate) {
        this.name = name;
        this.creatorID = creatorID;
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
}
