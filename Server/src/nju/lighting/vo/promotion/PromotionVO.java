package nju.lighting.vo.promotion;


<<<<<<< HEAD:Server/src/nju/lighting/vo/promotion/PromotionVO.java
<<<<<<< HEAD:Server/src/nju/lighting/vo/promotion/PromotionVO.java
import nju.lighting.po.CustomerGrade;
import nju.lighting.vo.giftdoc.GiftItemVO;
=======
>>>>>>> 4f84987ca2f7f5dd04190be0b6f3ae85b88084a1:Server/src/nju/lighting/vo/PromotionVO.java
=======
>>>>>>> 4f84987ca2f7f5dd04190be0b6f3ae85b88084a1:Server/src/nju/lighting/vo/PromotionVO.java
import shared.PromotionType;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created on 2017/10/21.
 * Description:
 * @author 陈俊宇
 */
public class PromotionVO {
    private PromotionType type;  //促销策略类型

    private Date startDate;  //起始日期

    private Date endDate;  //截止日期

    private Date time;

    private CustomerGrade level; //针对不同级别用户中用户的级别

    private double total;  //针对不同总价的促销策略中的总价

    private ArrayList<GiftItemVO> gifts;   //赠品列表

    private double off;  //折让金额

    private double vouchers; //代金券

    private Date voucherEndDate;  //代金券截止日期

    public PromotionVO(PromotionType type, Date startDate, Date endDate, Date time,CustomerGrade level, double total,
                      ArrayList<GiftItemVO> gifts, double off, double vouchers, Date voucherEndDate) {
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.level = level;
        this.total = total;

        this.gifts = gifts;
        this.off = off;
        this.vouchers = vouchers;
        this.voucherEndDate = voucherEndDate;
    }

    public void setStartDate(Date startDate){
        this.startDate=startDate;
    }

    public void setEndDate(Date endDate){
        this.endDate=endDate;
    }

    public void setLevel(CustomerGrade level){
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

    public void setVoucherEndDate(Date voucherEndDate) {
        this.voucherEndDate = voucherEndDate;
    }

    public void setGifts(ArrayList<GiftItemVO> gifts){
        this.gifts=gifts;
    }

    public void addGift(GiftItemVO gift){
        gifts.add(gift);
    }

    public void removeGift(GiftItemVO gift){
        gifts.remove(gift);
    }

    public PromotionType getType(){
        return type;
    }

    public CustomerGrade getLevel(){
        return  level;
    }

    public double getTotal(){
        return total;
    }

    public double getOff(){
        return  off;
    }

    public ArrayList<GiftItemVO> getGifts(){
        return gifts;
    }

    public Date getStartDate(){return  startDate;}

    public Date getEndDate(){return  endDate;}

    public double getVouchers(){
        return vouchers;
    }

    public Date getVoucherEndDate() {
        return voucherEndDate;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
