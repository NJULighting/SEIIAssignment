package nju.lighting.bl.commoditybl;

import nju.lighting.blservice.commodityblservice.CommodityBLService;
import nju.lighting.vo.commodity.CommodityCategoriesTreeVO;
import nju.lighting.vo.commodity.CommodityCategoryVO;
import nju.lighting.vo.commodity.CommodityItemVO;
import shared.ResultMessage;

import java.util.List;

public class CommodityController implements CommodityBLService {


    @Override
    public CommodityCategoriesTreeVO getCommodityCategoriesTreeVO() {
        return null;
    }

    @Override
    public ResultMessage addCommodity(CommodityItemVO newCommodity)  {
        return null;
    }

    @Override
    public List<CommodityItemVO> findCommodityVOByName(String commodityName) {
        return null;
    }

    @Override
    public CommodityItemVO findCommodityVOById(String id) {
        return null;
    }

    @Override
    public ResultMessage deleteCommodity(String id)  {
        return null;
    }

    @Override
    public ResultMessage modifyCommodity(CommodityItemVO commodity)  {
        return null;
    }

    @Override
    public ResultMessage addCategory(CommodityCategoryVO newCategory)  {
        return null;
    }

    @Override
    public ResultMessage deleteCategory(int id)  {
        return null;
    }

    @Override
    public ResultMessage modifyCategory(CommodityCategoryVO categoryItemVO) {
        return null;
    }
}
