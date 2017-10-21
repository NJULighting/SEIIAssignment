package nju.lighting.businesslogicservice.documentblservice;

import nju.lighting.vo.SalesReturnDocVO;

import java.rmi.RemoteException;

public interface SalesReturnDocBLService {

    public boolean addSaleReturnDoc(SalesReturnDocVO salesReturnDoc) throws RemoteException;
}
