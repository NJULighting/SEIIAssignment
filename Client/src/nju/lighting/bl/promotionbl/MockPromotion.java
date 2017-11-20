package nju.lighting.bl.promotionbl;

import shared.PromotionType;

/**
 * Created on 2017/11/19.
 * Description
 *
 * @author 陈俊宇
 */
public class MockPromotion extends Promotion {
    double voucher;
    PromotionType type;

    public MockPromotion(double voucher, PromotionType type) {
        this.voucher = voucher;
        this.type = type;
    }
}
