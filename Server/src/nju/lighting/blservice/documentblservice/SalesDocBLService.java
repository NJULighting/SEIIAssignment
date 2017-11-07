package nju.lighting.blservice.documentblservice;

import nju.lighting.vo.ResultMessage;

import java.rmi.RemoteException;

public interface SalesDocBLService {

    public ResultMessage addSalesDoc(SalesDocVO salesDoc) throws RemoteException;
}
