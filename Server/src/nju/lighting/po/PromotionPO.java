package nju.lighting.po;

import java.util.ArrayList;


/**
 * Created on 2017/10/19.
 * Description:
 * @author 陈俊宇
 */
public class PromotionPO {
    private int tag;  //促销策略类型

    private long startDate;  //起始日期

    private long endDate;  //截止日期

    private CustomerGrade level; //针对不同级别用户中用户的级别

    private double total;  //针对不同总价的促销策略中的总价

    private ArrayList<String> goods;  //组合商品

    private ArrayList<String> gifts;   //赠品列表

    private double off;  //折让金额

    private double vouchers; //代金券

    private long vouchersEndDate;  //代金券截止日期

    public PromotionPO(int tag, long startDate, long endDate, CustomerGrade level,
                       double total, ArrayList<String> goods, ArrayList<String> gifts, double off, double vouchers, long vouchersEndDate) {
        this.tag = tag;
        this.startDate = startDate;
        this.endDate = endDate;
        this.level = level;
        this.total = total;
        this.goods = goods;
        this.gifts = gifts;
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

    public void addGift(String gift) {
        gifts.add(gift);
    }

    public void removeGift(String gift) {
        gifts.remove(gift);
    }

    public int getTag() {
        return tag;
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

    public ArrayList getGifts() {
        return gifts;
    }

    public void setGifts(ArrayList gifts) {
        this.gifts = gifts;
    }

    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }

    public long getEndDate() {
        return endDate;
    }

    public void setEndDate(long endDate) {
        this.endDate = endDate;
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
