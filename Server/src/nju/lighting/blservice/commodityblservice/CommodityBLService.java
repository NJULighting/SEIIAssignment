package nju.lighting.blservice.commodityblservice;

import nju.lighting.vo.CommodityVO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface CommodityBLService extends Remote {

    ArrayList<CommodityVO> getCommodityList() throws RemoteException;

    boolean addCommodity(CommodityVO newCommodity) throws RemoteException;

    ArrayList<CommodityVO> findCommodityByName(String commodityName) throws RemoteException;

    CommodityVO findCommodityById(String id) throws RemoteException;

    boolean deleteCommodity(String id) throws RemoteException;

    boolean modifyCommodity(CommodityVO commodity) throws RemoteException;

}
