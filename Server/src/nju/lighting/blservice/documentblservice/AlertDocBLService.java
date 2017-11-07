package nju.lighting.blservice.documentblservice;

import shared.ResultMessage;
import nju.lighting.vo.AlertDocVO;
import nju.lighting.vo.RepositoryChangeVO;

import java.util.ArrayList;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AlertDocBLService extends Remote{

    ResultMessage addAlertDoc(AlertDocVO alertDoc) throws RemoteException;

    AlertDocVO getAlertDoc(String docID) throws RemoteException;

    ArrayList<AlertDocVO> getAlertDocs(String userid) throws RemoteException;

    void triggered(RepositoryChangeVO change) throws RemoteException;

}
