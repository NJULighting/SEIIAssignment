package nju.lighting.bl.commoditybl;

import java.util.List;

public interface CommodityInfo {

    CommodityCategoriesTree getCommodityTree();

    List<BasicCommodityItem> getBasicCommodityItems(List<String> ids);

    List<CommodityItem> getCommodityItems(List<String> ids);

    boolean addCommodityItem(String id, int count);

    boolean subCommodityItem(String id, int count);

}
