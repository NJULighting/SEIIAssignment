package nju.lighting.dataservice.commoditydataservice;

import nju.lighting.po.CommodityPO;
import nju.lighting.po.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface CommodityDataService extends Remote{

    ArrayList<CommodityPO> getAll() throws RemoteException;

    ResultMessage insert(CommodityPO pp) throws RemoteException;

    ArrayList<CommodityPO> findByName(String commodityName) throws RemoteException;

    CommodityPO find(String id) throws RemoteException;

    ResultMessage delete(String id) throws RemoteException;

    ResultMessage update(CommodityPO commodity);

}
