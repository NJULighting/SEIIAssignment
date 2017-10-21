package nju.lighting.dataservice.commoditydataservice;

import nju.lighting.po.CommodityPO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface CommodityDataService extends Remote{

    ArrayList<CommodityPO> getAll() throws RemoteException;

    boolean insert(CommodityPO pp) throws RemoteException;

    ArrayList<CommodityPO> findByName(String commodityName) throws RemoteException;

    CommodityPO find(String id) throws RemoteException;

    boolean delete(String id) throws RemoteException;

    boolean update(CommodityPO commodity);

}
