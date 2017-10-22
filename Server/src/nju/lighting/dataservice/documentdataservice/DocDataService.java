package nju.lighting.dataservice.documentdataservice;

import nju.lighting.po.DocPO;
import nju.lighting.po.InitPO;
import nju.lighting.po.ResultMessage;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface DocDataService {

    ResultMessage insert(DocPO doc) throws RemoteException;

    ArrayList<DocPO> findByUser(String user) throws RemoteException;

    DocPO find(String id) throws RemoteException;

    ResultMessage update(DocPO doc) throws RemoteException;

    ArrayList<DocPO> findByTime(long start, long end) throws RemoteException;

    ResultMessage newAccount(InitPO po) throws RemoteException;

    ArrayList<InitPO> getInitInfo() throws RemoteException;

    void init() throws RemoteException;

    void finish() throws RemoteException;
}
