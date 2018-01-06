package nju.lighting.blservice.commodityblservice;

import javafx.scene.image.Image;
import shared.CommodityBuildInfo;
import nju.lighting.vo.commodity.BasicCommodityItemVO;
import nju.lighting.vo.commodity.CommodityCategoriesTreeVO;
import nju.lighting.vo.commodity.CommodityCategoryVO;
import nju.lighting.vo.commodity.CommodityItemVO;
import shared.Result;
import shared.ResultMessage;

import java.util.List;

public interface CommodityBLService {

    /**
     * Used to separate commodity's id. Just like the document system's separator
     */
    String SEPARATOR = "-";

    /**
     * Get a tree of commodity categories. The root of the tree should be treated as a dummy,
     * the children of it are real data.
     * @return tree vo
     */
    CommodityCategoriesTreeVO getCommodityCategoriesTreeVO();

    /**
     * Add a new commodity to the system. You need to pass category's id for the new commodity,
     * 'cause <tt>CommodityVO</tt> doesn't contains its category's id.
     * @param builder new commodity's builder
     * @return <tt>[SUCCESS, ID]</tt> if category's id is right and database works well, <tt>[FAILURE, null]</tt> otherwise
     */
    Result<CommodityItemVO> addCommodity(CommodityBuildInfo.CommodityBuilder builder);

    /**
     * Find commodities by id of its parent category
     * @param categoryID if of the category
     * @return list of commodities that belongs to the same category
     */
    List<CommodityItemVO> findCommodityByCategory(int categoryID);

    /**
     * Basic commodity vo version of <tt>findCommodityByCategory(int)</tt>
     * @param categoryID id of the category
     * @return list of commodities(basic commodity vo) that belongs to the same category
     */
    List<BasicCommodityItemVO> findBasicCommodityByCategory(int categoryID);

    /**
     * Find commodities by id of its parent category
     * @param commodityName name of the commodity
     * @return list of commodities with the same name
     */
    List<CommodityItemVO> findCommodityVOByName(String commodityName);

    /**
     * Get a commodity by passing its id
     * @param id id of the commodity
     * @return corresponding commodity vo if found, <tt>null</tt> otherwise
     */
    CommodityItemVO findCommodityVOById(String id);


    List<CommodityItemVO> searchCommodity(String keyword);

    /**
     * Delete a commodity
     * @param id id of the commodity
     * @return <tt>SUCCESS</tt> as long as network and database works well, <tt>NETWORK_FAIL</tt> otherwise
     */
    ResultMessage deleteCommodity(String id);

    /**
     * Alter commodity's fields. The id of the commodity mustn't be changed
     * @param commodity changed commodity
     * @return <tt>SUCCESS</tt> if modify it successfully,
     * <tt>INVALID_ID</tt> if commodity's id is wrong,
     * <tt>NETWORK_FAIL</tt> if network fails
     */
    ResultMessage modifyCommodity(CommodityItemVO commodity);

    /**
     * Add a new category. The parent category's vo should be obtained in the vo tree.
     * @param newCategory category you want to add
     * @return <tt>SUCCESS</tt> if add successfully,
     * <tt>FAILURE</tt> if parent category don't exists any more or it contains commodity items.
     */
    Result<CommodityCategoryVO> addCategory(CommodityCategoryVO newCategory);

    /**
     * Delete a category. The id of the category can be obtained from the vo.
     * @param id id of the category
     * @return <tt>SUCCESS</tt> as long as network and database works well,
     * <tt>NETWORK_FAIL</tt> otherwise
     */
    ResultMessage deleteCategory(int id);

    /**
     * Change a category's name. We don't allow to change other attributes of
     * a category, 'cause we think it's trivial. Category vo should be obtained
     * from the categories tree vo. This method don't allow to change name of the
     * dummy node(the root node).
     * @param categoryVO vo of the category
     * @return <tt>SUCCESS</tt> if modify successfully, <tt>FAILURE</tt> if try
     * to change the dummy node or the category vo has a invalid path. <tt>NETWORK_FAIL</tt>
     * if network fails.
     */
    ResultMessage changeCategoryName(CommodityCategoryVO categoryVO);

    Image getTrend(String commodityId);
}
