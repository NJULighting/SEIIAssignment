package nju.lighting.dataservice.commoditydataservice;

import nju.lighting.po.commodity.CommodityCategoryPO;
import nju.lighting.po.commodity.CommodityItemPO;
import shared.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

public interface CommodityDataService extends Remote {

    List<CommodityItemPO> findByCategory(int categoryID) throws RemoteException;

    Date getRecentChangeTime() throws RemoteException;

    List<CommodityItemPO> getAllCommodity() throws RemoteException;

    CommodityItemPO findById(String id) throws RemoteException;

    ResultMessage add(CommodityItemPO commodityItemPO) throws RemoteException;

    ResultMessage update(CommodityItemPO commodityItemPO) throws RemoteException;

    ResultMessage update(CommodityCategoryPO categoryPO) throws RemoteException;

    ResultMessage deleteCommodity(String id) throws RemoteException;

    List<CommodityItemPO> findByName(String name) throws RemoteException;

    List<CommodityCategoryPO> getAllCommodityCategory() throws RemoteException;

    ResultMessage add(CommodityCategoryPO commodityCategoryPO) throws RemoteException;

    ResultMessage deleteCategory(int id) throws RemoteException;

}
