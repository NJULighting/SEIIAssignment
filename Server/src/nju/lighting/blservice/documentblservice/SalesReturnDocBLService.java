package nju.lighting.blservice.documentblservice;

import nju.lighting.vo.ResultMessage;
import nju.lighting.vo.SalesReturnDocVO;

import java.rmi.RemoteException;

public interface SalesReturnDocBLService {

    public ResultMessage addSaleReturnDoc(SalesReturnDocVO salesReturnDoc) throws RemoteException;
}
