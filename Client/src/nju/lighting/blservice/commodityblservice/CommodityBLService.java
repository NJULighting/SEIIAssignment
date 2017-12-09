package nju.lighting.blservice.commodityblservice;

import nju.lighting.vo.commodity.CommodityCategoriesTreeVO;
import nju.lighting.vo.commodity.CommodityCategoryVO;
import nju.lighting.vo.commodity.CommodityItemVO;
import shared.ResultMessage;

import java.util.List;

public interface CommodityBLService {

    CommodityCategoriesTreeVO getCommodityCategoriesTreeVO();

    ResultMessage addCommodity(CommodityItemVO newCommodity);

    List<CommodityItemVO> findCommodityByCategory(int categoryID);

    List<CommodityItemVO> findCommodityVOByName(String commodityName);

    CommodityItemVO findCommodityVOById(String id);

    ResultMessage deleteCommodity(String id);

    ResultMessage modifyCommodity(CommodityItemVO commodity);

    ResultMessage addCategory(CommodityCategoryVO newCategory);

    ResultMessage deleteCategory(int id);

    ResultMessage modifyCategory(CommodityCategoryVO categoryItemVO);
}
