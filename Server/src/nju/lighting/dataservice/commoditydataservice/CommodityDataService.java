package nju.lighting.dataservice.commoditydataservice;

import nju.lighting.po.commodity.CommodityItemPO;
import nju.lighting.po.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface CommodityDataService extends Remote{

    ArrayList<CommodityItemPO> getAll() throws RemoteException;

    ResultMessage insert(CommodityItemPO pp) throws RemoteException;

    ArrayList<CommodityItemPO> findByName(String commodityName) throws RemoteException;

    CommodityItemPO find(String id) throws RemoteException;

    ResultMessage delete(String id) throws RemoteException;

    ResultMessage update(CommodityItemPO commodity);

}
