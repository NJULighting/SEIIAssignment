package nju.lighting.dataservice.commoditydataservice;

import nju.lighting.po.commodity.CommodityCategoryPO;
import nju.lighting.po.commodity.CommodityItemPO;
import shared.ResultMessage;

import java.util.ArrayList;

public interface CommodityDataService {

    ArrayList<CommodityItemPO> getAllCommodity();

    CommodityItemPO findById(String id);

    ResultMessage add(CommodityItemPO commodityItemPO);

    ResultMessage update(CommodityItemPO commodityItemPO);

    ResultMessage deleteCommodity(String id);

    ArrayList<CommodityItemPO> findByName(String name);

    ArrayList<CommodityCategoryPO> getAllCommodityCategory();

    ResultMessage add(CommodityCategoryPO commodityCategoryPO);

    ResultMessage deleteCategory(int id);

}
