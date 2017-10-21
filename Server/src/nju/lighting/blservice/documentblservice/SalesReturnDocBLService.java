package nju.lighting.blservice.documentblservice;

import nju.lighting.vo.SalesReturnDocVO;

import java.rmi.RemoteException;

public interface SalesReturnDocBLService {

    public boolean addSaleReturnDoc(SalesReturnDocVO salesReturnDoc) throws RemoteException;
}
