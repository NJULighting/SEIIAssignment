package nju.lighting.blservice.documentblservice;

import nju.lighting.po.ResultMessage;
import nju.lighting.vo.StockDocVO;

import java.rmi.RemoteException;

public interface StockDocBLService {

    public ResultMessage addStockDoc(StockDocVO stockDoc) throws RemoteException;

}
