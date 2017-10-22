package nju.lighting.bl.documentbl;

import nju.lighting.blservice.documentblservice.SalesDocBLService;
import nju.lighting.vo.ResultMessage;
import nju.lighting.vo.SalesDocVO;

import java.rmi.RemoteException;

public class SalesDocBLService_Stub implements SalesDocBLService{

    public ResultMessage addSalesDoc(SalesDocVO salesDoc) throws RemoteException{
        return ResultMessage.SUCCESS;
    }
}
