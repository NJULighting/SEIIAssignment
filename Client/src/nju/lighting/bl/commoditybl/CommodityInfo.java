package nju.lighting.bl.commoditybl;

import nju.lighting.vo.commodity.BasicCommodityItemVO;
import nju.lighting.vo.repository.RepositoryTableItemVO;

import java.util.List;

public interface CommodityInfo {

    CommodityCategoriesTree getCommodityCategoryTree();

    BasicCommodityItemVO getBasicCommodityItemVO(String id);

    double getCommodityRecentSellPrice(String id);

    List<CommodityItem> getCommodityItems(List<String> ids);

    String getCommodityNameByID(String commodityID);

    List<RepositoryTableItemVO> getRepositoryTableItemList();

    boolean addCommodityItem(String id, int count);

    boolean reduceCommodityItem(String id, int count);

}
