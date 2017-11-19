package nju.lighting.bl.promotionbl;

import nju.lighting.vo.promotion.PromotionVO;
import shared.PromotionType;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created on 2017/11/7.
 * Description
 *
 * @author 陈俊宇
 */
public class MockPromotion extends Promotion {

    public ArrayList<PromotionVO> getBenefitsPlan() {

        Calendar c = Calendar.getInstance();
        PromotionVO A = new PromotionVO(PromotionType.Combo, null, null, null, null, 150, null, 20, 0, null);
        ArrayList<PromotionVO> promotions = new ArrayList<>();
        promotions.add(A);
        return promotions;
    }
}
