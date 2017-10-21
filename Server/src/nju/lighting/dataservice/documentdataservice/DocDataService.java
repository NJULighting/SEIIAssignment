package nju.lighting.dataservice.documentdataservice;

import nju.lighting.po.DocPO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface DocDataService {

    boolean insert(DocPO doc) throws RemoteException;

    ArrayList<DocPO> findByUser(String user) throws RemoteException;

    DocPO find(String id) throws RemoteException;

    boolean update(DocPO doc) throws RemoteException;

    ArrayList<DocPO> findByTime(long start, long end) throws RemoteException;

}
