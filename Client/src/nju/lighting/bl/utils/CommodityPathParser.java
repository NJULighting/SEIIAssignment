package nju.lighting.bl.utils;

import nju.lighting.blservice.commodityblservice.CommodityBLService;

/**
 * Created on 2017/12/9.
 * Description:
 * @author Liao
 */
public class CommodityPathParser {
    private static final String SEPARATOR = CommodityBLService.SEPARATOR;

    public static int getLastNumOfPath(String path) {
        int index = path.lastIndexOf(SEPARATOR);
        return Integer.parseInt(path.substring(index + 1));
    }
}
