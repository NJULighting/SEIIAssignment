package nju.lighting.blservice.documentblservice;

import nju.lighting.vo.SalesDocVO;

import java.rmi.RemoteException;

public interface SalesDocBLService {

    public boolean addSalesDoc(SalesDocVO salesDoc) throws RemoteException;
}
