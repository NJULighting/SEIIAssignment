package nju.lighting.vo.promotion;

import nju.lighting.vo.UserVO;
import nju.lighting.vo.doc.giftdoc.GiftItemVO;
import shared.CustomerGrade;
import shared.PromotionType;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created on 2017/11/19.
 * Description
 *
 * @author 陈俊宇
 */
public class CustomerOrientedPromotionVO extends PromotionVO{

    private CustomerGrade level; //用户的级别

    private ArrayList<GiftItemVO> gifts;   //赠品列表

    private double off;  //折让金额

    private double vouchers; //代金券

    private Date voucherEndDate;  //代金券截止日期

    public CustomerOrientedPromotionVO(String name, UserVO creator, PromotionType type, Date startDate,
                                       Date endDate, CustomerGrade level, ArrayList<GiftItemVO> gifts,
                                       double off, double vouchers, Date voucherEndDate) {
        super(name, creator, type, startDate, endDate);
        this.level = level;
        this.gifts = gifts;
        this.off = off;
        this.vouchers = vouchers;
        this.voucherEndDate = voucherEndDate;
    }

    public CustomerGrade getLevel() {
        return level;
    }

    public ArrayList<GiftItemVO> getGifts() {
        return gifts;
    }

    public double getOff() {
        return off;
    }

    public double getVouchers() {
        return vouchers;
    }

    public Date getVoucherEndDate() {
        return voucherEndDate;
    }
}
