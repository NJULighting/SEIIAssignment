package nju.lighting.po.promotion;


import shared.CustomerGrade;
import shared.PromotionType;

import java.util.ArrayList;
import java.util.Date;


/**
 * Created on 2017/10/19.
 * Description:
 * @author 陈俊宇
 */
public class PromotionPO {
    private PromotionType type;  //促销策略类型

    private Date startDate;  //起始日期

    private Date endDate;  //截止日期

    private Date time; //创建时间

    private CustomerGrade level; //针对不同级别用户中用户的级别

    private double total;  //针对不同总价的促销策略中的总价

    private ArrayList<String> goods;  //组合商品


    private double off;  //折让金额

    private double vouchers; //代金券金额

    private long vouchersEndDate;  //代金券截止日期

    public PromotionPO(PromotionType type, Date startDate, Date endDate, CustomerGrade level,
                       double total, ArrayList<String> goods, double off, double vouchers, long vouchersEndDate) {
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.level = level;
        this.total = total;
        this.goods = goods;
        this.off = off;
        this.vouchers = vouchers;
        this.vouchersEndDate = vouchersEndDate;
    }

    public void addGood(String good) {
        goods.add(good);
    }

    public void removeGood(String good) {
        goods.remove(good);
    }

    public PromotionType getType() {
        return type;
    }

    public CustomerGrade getLevel() {
        return level;
    }

    public void setLevel(CustomerGrade level) {
        this.level = level;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getOff() {
        return off;
    }

    public void setOff(double off) {
        this.off = off;
    }

    public ArrayList getGoods() {
        return goods;
    }

    public void setGoods(ArrayList goods) {
        this.goods = goods;
    }


    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public double getVouchers() {
        return vouchers;
    }

    public void setVouchers(double vouchers) {
        this.vouchers = vouchers;
    }

    public long getVouchersEndDate() {
        return vouchersEndDate;
    }

    public void setVouchersEndDate(long vouchersEndDate) {
        this.vouchersEndDate = vouchersEndDate;
    }

}
