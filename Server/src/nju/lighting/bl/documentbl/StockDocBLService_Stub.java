package nju.lighting.bl.documentbl;

import nju.lighting.blservice.documentblservice.StockDocBLService;
import nju.lighting.vo.ResultMessage;

import java.rmi.RemoteException;

public class StockDocBLService_Stub implements StockDocBLService{

    public ResultMessage addStockDoc(StockDocVO stockDoc) throws RemoteException{
        return ResultMessage.SUCCESS;
    }

}
