package nju.lighting.dataservice.approvaldataservice;

import nju.lighting.po.doc.historydoc.HistoryDocPO;
import shared.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created on 2017/10/21.
 * Description
 * @author 陈俊宇
 */
public interface ApprovalDataService extends Remote {
    ArrayList<HistoryDocPO> getDocumentList() throws RemoteException;

    ResultMessage update(HistoryDocPO po) throws RemoteException;

}
