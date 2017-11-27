package nju.lighting.dataservice.documentdataservice;

import nju.lighting.po.doc.DocPO;
import shared.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

public interface DocDataService extends Remote {

    String create(DocType type) throws RemoteException;

    ResultMessage commitDoc(DocPO doc) throws RemoteException;

    List<DocPO> findByUserId(String id) throws RemoteException;

    List<DocPO> findByState(DocState docState) throws RemoteException;

    List<DocPO> findByType(DocType type) throws RemoteException;

    List<DocPO> findByTime(Date from, Date to) throws RemoteException;

    List<DocPO> findByTimeAndType(Date from, Date to, DocType type) throws RemoteException;

}
