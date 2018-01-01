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
        // Check whether is the root
        if (path.isEmpty()) {
            return -1;
        }

        // Not the root
        int index = path.lastIndexOf(SEPARATOR);
        return Integer.parseInt(path.substring(index + 1));
    }

    public static int getCommodityCategory(String commodityPath) {
        // Check the commodity whether is the child of the root
        if (!commodityPath.contains(SEPARATOR))
            return -1;

        // Get its category id
        String[] tmp = commodityPath.split(SEPARATOR);
        return Integer.parseInt(tmp[tmp.length - 2]);
    }

    // Suppress instantiation
    private CommodityPathParser() {
        throw new AssertionError();
    }
}
