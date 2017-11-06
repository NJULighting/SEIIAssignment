package nju.lighting.data;

import nju.lighting.dataservice.commoditydataservice.CommodityDataService;
import nju.lighting.po.ResultMessage;
import nju.lighting.po.commodity.CommodityCategoryPO;
import nju.lighting.po.commodity.CommodityItemPO;

import java.util.ArrayList;

public class CommodityDataService_Stub implements CommodityDataService {

    @Override
    public ArrayList<CommodityItemPO> getAllCommodity() {
        return null;
    }

    @Override
    public CommodityItemPO findById(String id) {
        return null;
    }

    @Override
    public ResultMessage add(CommodityItemPO commodityItemPO) {
        return null;
    }

    @Override
    public ResultMessage update(CommodityItemPO commodityItemPO) {
        return null;
    }

    @Override
    public ResultMessage deleteCommodity(String id) {
        return null;
    }

    @Override
    public ResultMessage findBuName(String name) {
        return null;
    }

    @Override
    public ArrayList<CommodityCategoryPO> getAllCommodityCategory() {
        return null;
    }

    @Override
    public ResultMessage add(CommodityCategoryPO commodityCategoryPO) {
        return null;
    }

    @Override
    public ResultMessage deleteCategory(int id) {
        return null;
    }

    public CommodityDataService_Stub() {

    }
}
