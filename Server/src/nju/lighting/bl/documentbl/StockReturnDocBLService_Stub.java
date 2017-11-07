package nju.lighting.bl.documentbl;

import nju.lighting.blservice.documentblservice.StockReturnDocBLService;
import nju.lighting.vo.ResultMessage;

import java.rmi.RemoteException;

public class StockReturnDocBLService_Stub implements StockReturnDocBLService{

    public ResultMessage addStockReturnDoc(StockReturnDocVO stockReturnDoc) throws RemoteException{
        return ResultMessage.SUCCESS;
    }
}
