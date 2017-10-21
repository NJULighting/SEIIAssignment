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

    private int level; //针对不同级别用户中用户的级别

    private double total;  //针对不同总价的促销策略中的总价

    private ArrayList<String> goods;  //组合商品

    private ArrayList<String> gifts;   //赠品列表

    private double off;  //折让金额

    private double vouchers; //代金券

    private long vouchersEndDate;  //代金券截止日期

    public PromotionPO(int tag,long startDate,long endDate){
        this.tag=tag;
        this.startDate=startDate;
        this.endDate=endDate;
    }

    public void setStartDate(long startDate){
        this.startDate=startDate;
    }

    public void setEndDate(long endDate){
        this.endDate=endDate;
    }

    public void setLevel(int level){
        this.level=level;
    }

    public void setTotal(double total){
        this.total=total;
    }

    public void setOff(double off){
        this.off=off;
    }

    public void setVouchers(double vouchers){
        this.vouchers=vouchers;
    }

    public void setVouchersEndDate(long vouchersEndDate) {
        this.vouchersEndDate = vouchersEndDate;
    }
    public void setGoods(ArrayList goods){
        this.goods=goods;
    }

    public void addGood(String good){
        goods.add(good);
    }

    public void removeGood(String good){
        goods.remove(good);
    }

    public void setGifts(ArrayList gifts){
        this.gifts=gifts;
    }

    public void addGift(String gift){
        gifts.add(gift);
    }

    public void removeGift(String gift){
        gifts.remove(gift);
    }

    public int getTag(){
        return tag;
    }

    public int getLevel(){
        return  level;
    }

    public double getTotal(){
        return total;
    }

    public double getOff(){
        return  off;
    }

    public  ArrayList getGoods(){
        return  goods;
    }

    public ArrayList getGifts(){
        return gifts;
    }

    public long getStartDate(){return  startDate;}

    public long getEndDate(){return  endDate;}

    public double getVouchers(){
        return vouchers;
    }

    public long getVouchersEndDate() {
        return vouchersEndDate;
    }

}
