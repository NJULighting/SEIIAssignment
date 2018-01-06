package nju.lighting.dataservice.documentdataservice;

import nju.lighting.po.doc.DocPO;
import shared.DocState;
import shared.DocType;
import shared.Result;
import shared.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

public interface DocDataService extends Remote {
    Result<String> commitDoc(DocPO doc) throws RemoteException;

    ResultMessage updateDoc(DocPO doc) throws RemoteException;

    List<DocPO> findByUserId(String id) throws RemoteException;

    List<DocPO> findByState(DocState docState) throws RemoteException;

    List<DocPO> findByType(DocType type) throws RemoteException;

    List<DocPO> findByTime(Date from, Date to) throws RemoteException;

    List<DocPO> findByTimeAndType(Date from, Date to, DocType type) throws RemoteException;

    List<DocPO> findByTypeAndState(DocType type, DocState state) throws RemoteException;

    void sentMail(String creatorId, String header, String content) throws RemoteException;
}
