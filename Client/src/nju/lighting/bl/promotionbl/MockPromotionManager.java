package nju.lighting.bl.promotionbl;

import shared.PromotionType;

import java.util.ArrayList;

/**
 * Created on 2017/11/7.
 * Description
 *
 * @author 陈俊宇
 */
public class MockPromotionManager extends PromotionManager {

    public MockPromotionManager(){
        MockPromotion promotion1=new MockPromotion(5,PromotionType.PriceOriented);
        MockPromotion promotion2=new MockPromotion(10,PromotionType.CustomerOriented);
        promotions.add(promotion1);
        promotions.add(promotion2);
    }

    public ArrayList<Promotion> getBenefitsPlan(){
        return promotions;
    }

}
