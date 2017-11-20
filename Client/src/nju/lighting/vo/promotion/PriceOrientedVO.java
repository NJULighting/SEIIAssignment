package nju.lighting.vo.promotion;

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
public class PriceOrientedVO extends PromotionVO {
    private double price;   //目标金额

    private ArrayList<GiftItemVO> gifts;   //赠品列表

    private double vouchers; //代金券

    private Date voucherEndDate;  //代金券截止日期

    public PriceOrientedVO(String name, PromotionType type, Date startDate, Date endDate, double price,
                           ArrayList<GiftItemVO> gifts, double vouchers, Date voucherEndDate) {
        super(name, type, startDate, endDate);
        this.price = price;
        this.gifts = gifts;
        this.vouchers = vouchers;
        this.voucherEndDate = voucherEndDate;
    }

    public double getPrice() {
        return price;
    }

    public ArrayList<GiftItemVO> getGifts() {
        return gifts;
    }

    public double getVouchers() {
        return vouchers;
    }

    public Date getVoucherEndDate() {
        return voucherEndDate;
    }
}
