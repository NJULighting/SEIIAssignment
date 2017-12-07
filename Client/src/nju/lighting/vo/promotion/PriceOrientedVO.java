package nju.lighting.vo.promotion;

import nju.lighting.vo.UserVO;
import nju.lighting.vo.doc.giftdoc.GiftItemListVO;
import nju.lighting.vo.doc.giftdoc.GiftItemVO;
import shared.PromotionType;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created on 2017/11/19.
 * Description
 *
 * @author 陈俊宇
 */
public class
PriceOrientedVO extends PromotionVO {
    private double price;   //目标金额

    private double off;

    private GiftItemListVO gifts;   //赠品列表

    private double vouchers; //代金券

    private Date voucherEndDate;  //代金券截止日期

    public PriceOrientedVO(String name, UserVO creator, PromotionType type, Date startDate, Date endDate,
                           double price, double off, ArrayList<GiftItemVO> gifts, double vouchers,
                           Date voucherEndDate) {
        super(name, creator, type, startDate, endDate);
        this.price = price;
        this.off = off;

        if (gifts != null)
            this.gifts = new GiftItemListVO(gifts);
        this.vouchers = vouchers;
        this.voucherEndDate = voucherEndDate;
    }

    public double getOff() {
        return off;
    }

    public double getPrice() {
        return price;
    }

    public GiftItemListVO getGifts() {
        return gifts;
    }

    public double getVouchers() {
        return vouchers;
    }

    public Date getVoucherEndDate() {
        return voucherEndDate;
    }
}
