package nju.lighting.dataservice.userdataservice;

import nju.lighting.po.user.UserPO;
import shared.Identity;
import shared.LoginReturnState;
import shared.ResultMessage;
import shared.TwoTuple;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created on 2017/10/22.
 * Description:
 * @author Liao
 */
public interface UserDataService extends Remote {

    ResultMessage insert(UserPO po) throws RemoteException;

    UserPO get(String id) throws RemoteException;

    ResultMessage update(UserPO po) throws RemoteException;

    ResultMessage delete(String id) throws RemoteException;

    List<UserPO> getAll() throws RemoteException;

    TwoTuple<UserPO, LoginReturnState> login(String id, String password) throws RemoteException;

}
