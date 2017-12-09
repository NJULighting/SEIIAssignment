package nju.lighting.bl.commoditybl;

import java.util.ArrayList;

public interface CommodityInfo {

    CommodityCategoriesTree getCommodityTree();

    ArrayList<BasicCommodityItem> getBasicCommodityItems(ArrayList<String> ids);

    ArrayList<CommodityItem> getCommodityItems(ArrayList<String> ids);

    boolean addCommodityItem(String id, int count);

    boolean subCommodityItem(String id, int count);

}
