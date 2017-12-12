package nju.lighting.bl.commoditybl;

import java.util.ArrayList;

/**
 * Created on 2017/12/9.
 * Description:
 * @author Liao
 */
public class CommodityInfoImpl implements CommodityInfo {
    @Override
    public CommodityCategoriesTree getCommodityTree() {
        return null;
    }

    @Override
    public ArrayList<BasicCommodityItem> getBasicCommodityItems(ArrayList<String> ids) {
        return null;
    }

    @Override
    public ArrayList<CommodityItem> getCommodityItems(ArrayList<String> ids) {
        return null;
    }

    @Override
    public boolean addCommodityItem(String id, int count) {
        return false;
    }

    @Override
    public boolean subCommodityItem(String id, int count) {
        return false;
    }
}
