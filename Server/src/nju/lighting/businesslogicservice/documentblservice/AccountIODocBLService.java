package nju.lighting.businesslogicservice.documentblservice;

import nju.lighting.po.AccountIODocPO;
import nju.lighting.po.AccountPO;
import nju.lighting.po.CustomerPO;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created on 2017/10/19.
 * Description:
 * @author Liao
 */
public interface AccountIODocBLService {
    AccountPO createAccountInOut() throws RemoteException;
    void commitDoc(AccountIODocPO docPO) throws RemoteException;
    String getState(String id) throws RemoteException;
    AccountIODocPO getHistoryDoc(String id) throws RemoteException;
    ArrayList<CustomerPO> getCustomerList() throws RemoteException;
    ArrayList<AccountPO> getAccountList() throws RemoteException;
}
