package nju.lighting.bl.commoditybl;

import java.util.List;

/**
 * Created on 2017/12/9.
 * Description:
 * @author Liao
 */
public class CommodityInfoImpl implements CommodityInfo {

    private CommodityManager manager = CommodityManager.INSTANCE;

    @Override
    public CommodityCategoriesTree getCommodityTree() {
        return null;
    }

    @Override
    public List<BasicCommodityItem> getBasicCommodityItems(List<String> ids) {
        return null;
    }

    @Override
    public List<CommodityItem> getCommodityItems(List<String> ids) {
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
