package nju.lighting.businesslogicservice.documentblservice;

import nju.lighting.po.AccountPO;
import nju.lighting.po.CostDocPO;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created on 2017/10/20.
 * Description:
 * @author Liao
 */
public interface CostDocBLService {
    CostDocPO createCostDoc() throws RemoteException;
    void commitDoc(CostDocPO costDocPO) throws RemoteException;
    String getState(String id) throws RemoteException;
    CostDocPO getHistoryDoc(String id) throws RemoteException;
    ArrayList<AccountPO> getAccountList() throws RemoteException;
}
