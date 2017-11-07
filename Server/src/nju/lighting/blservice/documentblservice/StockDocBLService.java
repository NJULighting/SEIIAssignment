package nju.lighting.blservice.documentblservice;

import nju.lighting.vo.ResultMessage;

import java.rmi.RemoteException;

public interface StockDocBLService {

    public ResultMessage addStockDoc(StockDocVO stockDoc) throws RemoteException;

}
