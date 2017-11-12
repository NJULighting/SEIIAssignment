package nju.lighting.blservice.commodityblservice;

import nju.lighting.vo.commodity.CommodityCategoryVO;
import nju.lighting.vo.commodity.CommodityItemVO;
import nju.lighting.vo.commodity.CommodityTreeVO;
import shared.ResultMessage;

import java.rmi.Remote;
import java.util.ArrayList;

public interface CommodityBLService extends Remote {

    CommodityTreeVO getCommodityTree() ;

    ResultMessage addCommodity(CommodityItemVO newCommodity) ;

    ArrayList<CommodityItemVO> findCommodityByName(String commodityName) ;

    CommodityItemVO findCommodityById(String id) ;

    ResultMessage deleteCommodity(String id) ;

    ResultMessage modifyCommodity(CommodityItemVO commodity) ;

    ResultMessage addCategory(CommodityCategoryVO newCategory) ;

    ResultMessage deleteCategory(int id) ;

    ResultMessage modifyCategory(CommodityCategoryVO categoryItemVO) ;

}
