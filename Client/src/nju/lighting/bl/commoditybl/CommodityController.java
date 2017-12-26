package nju.lighting.bl.commoditybl;

import nju.lighting.blservice.commodityblservice.CommodityBLService;
import nju.lighting.vo.commodity.BasicCommodityItemVO;
import nju.lighting.vo.commodity.CommodityCategoriesTreeVO;
import nju.lighting.vo.commodity.CommodityCategoryVO;
import nju.lighting.vo.commodity.CommodityItemVO;
import shared.ResultMessage;
import shared.TwoTuple;

import java.util.List;

class CommodityController implements CommodityBLService {
    private CommodityManager manager = CommodityManager.INSTANCE;

    @Override
    public CommodityCategoriesTreeVO getCommodityCategoriesTreeVO() {
        return manager.getCommodityCategoriesTreeVO();
    }

    @Override
    public TwoTuple<ResultMessage, String> addCommodity(CommodityItemVO newCommodity, CommodityCategoryVO category) {
        return manager.addCommodity(newCommodity, category.getPath());
    }

    @Override
    public List<CommodityItemVO> findCommodityByCategory(int categoryID) {
        return manager.findCommodityByCategory(categoryID);
    }

    @Override
    public List<BasicCommodityItemVO> findBasicCommodityByCategory(int categoryID) {
        return null;
    }

    @Override
    public List<CommodityItemVO> findCommodityVOByName(String commodityName) {
        return manager.findCommodityVOByName(commodityName);
    }

    @Override
    public List<CommodityItemVO> searchCommodity(String keyword) {
        return null;
    }

    @Override
    public CommodityItemVO findCommodityVOById(String id) {
        return manager.findCommodityVOById(id);
    }

    @Override
    public ResultMessage deleteCommodity(String id) {
        return manager.deleteCommodity(id);
    }

    @Override
    public ResultMessage modifyCommodity(CommodityItemVO commodity) {
        return manager.modifyCommodity(commodity);
    }

    @Override
    public TwoTuple<ResultMessage, Integer> addCategory(CommodityCategoryVO newCategory) {
        return manager.addCategory(newCategory);
    }

    @Override
    public ResultMessage deleteCategory(int id) {
        return manager.deleteCategory(id);
    }

    @Override
    public ResultMessage changeCategoryName(CommodityCategoryVO categoryVO) {
        return manager.changeCategoryName(categoryVO);
    }
}
