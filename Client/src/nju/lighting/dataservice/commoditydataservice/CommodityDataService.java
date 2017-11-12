package nju.lighting.dataservice.commoditydataservice;

import nju.lighting.po.commodity.CommodityCategoryPO;
import nju.lighting.po.commodity.CommodityItemPO;
import shared.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface CommodityDataService extends Remote {

    ArrayList<CommodityItemPO> getAllCommodity() throws RemoteException;

    CommodityItemPO findById(String id) throws RemoteException;

    ResultMessage add(CommodityItemPO commodityItemPO) throws RemoteException;

    ResultMessage update(CommodityItemPO commodityItemPO) throws RemoteException;

    ResultMessage deleteCommodity(String id) throws RemoteException;

    ArrayList<CommodityItemPO> findByName(String name) throws RemoteException;

    ArrayList<CommodityCategoryPO> getAllCommodityCategory() throws RemoteException;

    ResultMessage add(CommodityCategoryPO commodityCategoryPO) throws RemoteException;

    ResultMessage deleteCategory(int id) throws RemoteException;

}
