package nju.lighting.blservice.documentblservice;

import nju.lighting.po.ResultMessage;
import nju.lighting.vo.SalesDocVO;

import java.rmi.RemoteException;

public interface SalesDocBLService {

    public ResultMessage addSalesDoc(SalesDocVO salesDoc) throws RemoteException;
}
