package nju.lighting.bl.commoditybl;

import nju.lighting.blservice.commodityblservice.CommodityBLService;
import nju.lighting.vo.commodity.CommodityItemVO;
import nju.lighting.vo.commodity.CommodityTreeVO;
import nju.lighting.vo.commodity.CommodityCategoryVO;

import shared.ResultMessage;

import java.util.ArrayList;
import java.util.Date;

public class CommodityBLService_Stub implements CommodityBLService {

    public CommodityBLService_Stub() {
        CommodityItem com1 = new CommodityItem("01", "天才灯具", null, "2017",
                100, 1000, 1000, 1000, 1000, "a", "01", new Date(111111111));
        CommodityItem com2 = new CommodityItem("02", "天才灯炮", null, "2017",
                100, 1000, 1000, 1000, 1000, "a", "01", new Date(111111111));
        CommodityItem com3 = new CommodityItem("03", "小天才灯具", null, "2017",
                100, 1000, 1000, 1000, 1000, "a", "01", new Date(111111111));
        CommodityItem com4 = new CommodityItem("04", "小天才灯炮", null, "2017",
                100, 1000, 1000, 1000, 1000, "a", "01", new Date(111111111));
        CommodityItem com5 = new CommodityItem("05", "大天才灯具", null, "2017",
                100, 1000, 1000, 1000, 1000, "a", "01", new Date(111111111));
        CommodityItem com6 = new CommodityItem("06", "大天才灯具", null, "2017",
                100, 1000, 1000, 1000, 1000, "a", "01", new Date(111111111));
    }

    @Override
    public CommodityTreeVO getCommodityTreeVO() {
        return null;
    }

    @Override
    public ResultMessage addCommodity(CommodityItemVO newCommodity) {
        return null;
    }

    @Override
    public ArrayList<CommodityItemVO> findCommodityVOByName(String commodityName)  {
        return null;
    }

    @Override
    public CommodityItemVO findCommodityVOById(String id) {
        return null;
    }

    @Override
    public ResultMessage deleteCommodity(String id) {
        return null;
    }

    @Override
    public ResultMessage modifyCommodity(CommodityItemVO commodity) {
        return null;
    }

    @Override
    public ResultMessage addCategory(CommodityCategoryVO newCategory){
        return null;
    }

    @Override
    public ResultMessage deleteCategory(int id) {
        return null;
    }

    @Override
    public ResultMessage modifyCategory(CommodityCategoryVO categoryItemVO) {
        return null;
    }
}
