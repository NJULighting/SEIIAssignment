package nju.lighting.bl.promotionbl;

import nju.lighting.vo.promotion.PromotionVO;

import java.util.ArrayList;

/**
 * Created on 2017/11/14.
 * Description
 *
 * @author 陈俊宇
 */
public class PromotionManage {
    ArrayList<Promotion> promotions;

    public PromotionManage(){
        this.promotions=getPromotionList();
    }

    public ArrayList<Promotion> getPromotionList() {
        return null;
    }

    public ArrayList<Promotion> getBenefitsPlan() {
        return null;
    }
}
