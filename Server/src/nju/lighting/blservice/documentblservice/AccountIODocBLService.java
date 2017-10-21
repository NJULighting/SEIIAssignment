package nju.lighting.blservice.documentblservice;

import nju.lighting.po.CustomerPO;
import nju.lighting.po.ResultMessage;
import nju.lighting.vo.AccountIODocVO;
import nju.lighting.vo.AccountVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created on 2017/10/19.
 * Description:
 * @author Liao
 */
public interface AccountIODocBLService {
    AccountVO createAccountInOut() throws RemoteException;

    ResultMessage commitDoc(AccountIODocVO docVO) throws RemoteException;

    String getState(String id) throws RemoteException;

    AccountIODocVO getHistoryDoc(String id) throws RemoteException;

    ArrayList<CustomerPO> getCustomerList() throws RemoteException;

    ArrayList<AccountVO> getAccountList() throws RemoteException;
}
