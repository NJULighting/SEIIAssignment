package nju.lighting.blservice.commodityblservice;

import nju.lighting.vo.commodity.CommodityCategoriesTreeVO;
import nju.lighting.vo.commodity.CommodityCategoryVO;
import nju.lighting.vo.commodity.CommodityItemVO;
import shared.ResultMessage;

import java.util.List;

public interface CommodityBLService {

    String SEPARATOR = "-";

    CommodityCategoriesTreeVO getCommodityCategoriesTreeVO();

    /**
     * Add a new commodity to the system. You need to pass category's id for the new commodity,
     * 'cause <tt>CommodityVO</tt> doesn't contains its category's id.
     * @param newCommodity new commodity's vo
     * @param category   id of the commodity's category
     * @return <tt>SUCCESS</tt> if category's id is right and database works well, <tt>FAILURE</tt> otherwise
     */
    ResultMessage addCommodity(CommodityItemVO newCommodity, CommodityCategoryVO category);

    List<CommodityItemVO> findCommodityByCategory(int categoryID);

    List<CommodityItemVO> findCommodityVOByName(String commodityName);

    CommodityItemVO findCommodityVOById(String id);

    ResultMessage deleteCommodity(String id);

    ResultMessage modifyCommodity(CommodityItemVO commodity);

    ResultMessage addCategory(CommodityCategoryVO newCategory);

    ResultMessage deleteCategory(int id);

    ResultMessage changeCategoryName(CommodityCategoryVO categoryVO);
}
