package nju.lighting.bl.commoditybl;

import javafx.scene.image.Image;
import nju.lighting.blservice.commodityblservice.CommodityBLService;
import shared.CommodityBuildInfo;
import nju.lighting.vo.commodity.BasicCommodityItemVO;
import nju.lighting.vo.commodity.CommodityCategoriesTreeVO;
import nju.lighting.vo.commodity.CommodityCategoryVO;
import nju.lighting.vo.commodity.CommodityItemVO;
import shared.Result;
import shared.ResultMessage;

import java.util.List;

public class CommodityController implements CommodityBLService {
    private CommodityManager manager = CommodityManager.INSTANCE;

    @Override
    public CommodityCategoriesTreeVO getCommodityCategoriesTreeVO() {
        return manager.getCommodityCategoriesTreeVO();
    }

    @Override
    public Result<CommodityItemVO> addCommodity(CommodityBuildInfo.CommodityBuilder builder) {
        return manager.addCommodity(builder);
    }

    @Override
    public List<CommodityItemVO> findCommodityByCategory(int categoryID) {
        return manager.findCommodityByCategory(categoryID);
    }

    @Override
    public List<CommodityItemVO> findCommodityVOByName(String commodityName) {
        return manager.findCommodityVOByName(commodityName);
    }

    @Override
    public List<CommodityItemVO> searchCommodity(String keyword) {
        return manager.searchCommodity(keyword);
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
    public Result<CommodityCategoryVO> addCategory(CommodityCategoryVO newCategory) {
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
