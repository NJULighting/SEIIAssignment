package nju.lighting.presentation.factory;

import nju.lighting.bl.promotionbl.PromotionController;
import nju.lighting.blservice.promotionblservice.PromotionBLService;

/**
 * Created on 2017/12/30.
 * Description
 * @author 陈俊宇
 */
public class PromotionBLServiceFactory {
    private static PromotionBLService promotionBLService = new PromotionController();

    public static PromotionBLService getPromotionBLService() {
        return promotionBLService;
    }
}
