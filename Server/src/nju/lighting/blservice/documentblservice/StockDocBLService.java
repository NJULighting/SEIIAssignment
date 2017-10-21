package nju.lighting.blservice.documentblservice;

import nju.lighting.vo.StockDocVO;

import java.rmi.RemoteException;

public interface StockDocBLService {

    public boolean addStockDoc(StockDocVO stockDoc) throws RemoteException;

}
