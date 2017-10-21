package nju.lighting.businesslogicservice.documentblservice;

import nju.lighting.vo.SalesDocVO;

import java.rmi.RemoteException;

public interface SalesDocBLService {

    public boolean addSalesDoc(SalesDocVO stockDoc) throws RemoteException;
}
