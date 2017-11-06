package nju.lighting.blservice.commodityblservice;

import nju.lighting.po.ResultMessage;
import nju.lighting.vo.commodity.CommodityCategoryItemVO;
import nju.lighting.vo.commodity.CommodityItemVO;
import nju.lighting.vo.commodity.CommodityTreeVO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface CommodityBLService extends Remote {

    CommodityTreeVO getCommodityTree() throws RemoteException;

    ResultMessage addCommodity(CommodityItemVO newCommodity);

    ArrayList<CommodityItemVO> findCommodityByName(String commodityName);

    CommodityItemVO findCommodityById(String id);

    ResultMessage deleteCommodity(String id);

    ResultMessage modifyCommodity(CommodityItemVO commodity);

    ResultMessage addCategory(CommodityCategoryItemVO newCategory);

    ResultMessage deleteCategory(int id);

    ResultMessage modifyCategory(CommodityCategoryItemVO categoryItemVO);

}
