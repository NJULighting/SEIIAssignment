package nju.lighting.dataservice.userdataservice;

import nju.lighting.po.user.UserPO;
import shared.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created on 2017/10/22.
 * Description:
 * @author Liao
 */
public interface UserDataService extends Remote {
    void init() throws RemoteException;

    void finish() throws RemoteException;

    ResultMessage insert(UserPO po) throws RemoteException;

    ArrayList<UserPO> find(String keyword) throws RemoteException;

    UserPO get(String ID) throws RemoteException;

    ResultMessage update(UserPO po) throws RemoteException;

    ResultMessage delete(UserPO po) throws RemoteException;

    ArrayList<UserPO> getAll() throws RemoteException;

    ResultMessage logIn(UserPO po) throws RemoteException;

    ResultMessage logOut(UserPO po) throws RemoteException;
}
