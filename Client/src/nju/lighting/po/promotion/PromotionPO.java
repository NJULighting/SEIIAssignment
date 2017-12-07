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

    private int id; //id
    private String name; //促销策略名称
    private PromotionType type;  //促销策略类型
    private Date startDate;  //起始日期
    private Date endDate;  //截止日期
    private Date time; //创建时间
    private CustomerGrade level; //针对不同级别用户中用户的级别
    private double total;  //针对不同总价的促销策略中的总价
    private List<PromotionPackageItemPO> goods; //组合特价包裹
    private double off;  //折让金额
    private double vouchers; //代金券金额
    private Date vouchersEndDate;  //代金券截止日期

    public PromotionPO(int id, String name, PromotionType type, Date startDate, Date endDate, Date time, CustomerGrade level
            , double total, List<PromotionPackageItemPO> goods, double off, double vouchers, Date vouchersEndDate) {
        this.id = id;
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

    public PromotionPO(String name, PromotionType type, Date startDate, Date endDate, Date time, CustomerGrade level
            , double total, List<PromotionPackageItemPO> goods, double off, double vouchers, Date vouchersEndDate) {
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
}
