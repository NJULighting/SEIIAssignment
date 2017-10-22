package nju.lighting.blservice.documentblservice;

import nju.lighting.vo.ResultMessage;
import nju.lighting.vo.StockReturnDocVO;

import java.rmi.RemoteException;

public interface StockReturnDocBLService {

    public ResultMessage addStockReturnDoc(StockReturnDocVO stockReturnDoc) throws RemoteException;
}
