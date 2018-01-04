package nju.lighting.presentation.factory;

import nju.lighting.bl.commoditybl.CommodityController;
import nju.lighting.blservice.commodityblservice.CommodityBLService;

/**
 * Created on 2017/12/30.
 * Description
 * @author 陈俊宇
 */
public class CommodityBLServiceFactory {
    private static CommodityBLService commodityBLService = new CommodityController();

    public static CommodityBLService getCommodityBLService() {
        return commodityBLService;
    }
}
