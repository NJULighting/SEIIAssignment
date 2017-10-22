package nju.lighting.blservice.accountblservice;

import nju.lighting.vo.AccountVO;
import nju.lighting.vo.ResultMessage;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created on 2017/10/20.
 * Description:
 * @author Liao
 */
public interface AccountBLService {
    ArrayList<AccountVO> getAccountList() throws RemoteException;

    ResultMessage addAccount(String name, String amount) throws RemoteException;

    ArrayList<AccountVO> findAccount(String keyword) throws RemoteException;

    AccountVO getAccount(String id) throws RemoteException;

    ResultMessage deleteAccount(String id) throws RemoteException;

    ResultMessage modifyAccount(AccountVO vo) throws RemoteException;
}
