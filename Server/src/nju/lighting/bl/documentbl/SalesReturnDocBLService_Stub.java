package nju.lighting.bl.documentbl;

import nju.lighting.blservice.documentblservice.SalesReturnDocBLService;
import nju.lighting.vo.ResultMessage;
import nju.lighting.vo.SalesReturnDocVO;

import java.rmi.RemoteException;

public class SalesReturnDocBLService_Stub implements SalesReturnDocBLService{

    public ResultMessage addSaleReturnDoc(SalesReturnDocVO salesReturnDoc) throws RemoteException{
        return ResultMessage.SUCCESS;
    }
}
