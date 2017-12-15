package nju.lighting.bl.commoditybl;

import nju.lighting.vo.repository.RepositoryTableItemVO;

import java.util.List;

public interface CommodityInfo {

    CommodityCategoriesTree getCommodityCategoryTree();

    List<BasicCommodityItem> getBasicCommodityItems(List<String> ids);

    List<CommodityItem> getCommodityItems(List<String> ids);

    String getCommodityNameByID(String commodityID);

    List<RepositoryTableItemVO> getRepositoryTableItemList();

    boolean addCommodityItem(String id, int count);

    boolean subCommodityItem(String id, int count);

}
