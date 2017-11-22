package nju.lighting.dataservice.accountdataservice;

import nju.lighting.po.account.AccountPO;
import shared.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created on 2017/10/22.
 * Description:
 * @author Liao
 */
public interface AccountDataService extends Remote {
    ResultMessage insert(AccountPO po) throws RemoteException;

    AccountPO get(String id) throws RemoteException;

    ArrayList<AccountPO> getAll() throws RemoteException;

    ResultMessage delete(AccountPO po) throws RemoteException;

    void finish() throws RemoteException;

    void init() throws RemoteException;

    ResultMessage update(AccountPO po) throws RemoteException;
}
