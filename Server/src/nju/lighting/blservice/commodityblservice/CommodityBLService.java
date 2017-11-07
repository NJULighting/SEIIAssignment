package nju.lighting.blservice.commodityblservice;

import shared.ResultMessage;
import nju.lighting.vo.commodity.CommodityItemVO;
import nju.lighting.vo.commodity.CommodityTreeVO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface CommodityBLService extends Remote {

    CommodityTreeVO getCommodityTree() throws RemoteException;

    ResultMessage addCommodity(CommodityItemVO newCommodity) throws RemoteException;

    ArrayList<CommodityItemVO> findCommodityByName(String commodityName) throws RemoteException;

    CommodityItemVO findCommodityById(String id) throws RemoteException;

    ResultMessage deleteCommodity(String id) throws RemoteException;

    ResultMessage modifyCommodity(CommodityItemVO commodity) throws RemoteException;

    ResultMessage addCategory(CommodityCategoryItemVO newCategory) throws RemoteException;

    ResultMessage deleteCategory(int id) throws RemoteException;

    ResultMessage modifyCategory(CommodityCategoryItemVO categoryItemVO) throws RemoteException;

}
