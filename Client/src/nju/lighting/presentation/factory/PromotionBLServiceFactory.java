package nju.lighting.presentation.factory;

import nju.lighting.bl.promotionbl.PromotionBLService_Stub;
import nju.lighting.blservice.promotionblservice.PromotionBLService;

/**
 * Created on 2017/12/30.
 * Description
 *
 * @author 陈俊宇
 */
public class PromotionBLServiceFactory {
    private static PromotionBLService promotionBLService=new PromotionBLService_Stub();

    public static PromotionBLService getPromotionBLService() {
        return promotionBLService;
    }
}
