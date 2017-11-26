package nju.lighting.data.userdata;

import nju.lighting.dataservice.userdataservice.UserDataService;
import nju.lighting.po.UserPO;
import shared.LoginReturnState;
import shared.ResultMessage;
import shared.TwoTuple;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2017/11/26.
 * Description:
 *
 * @author iznauy
 */
public class UserData implements UserDataService {

    @Override
    public ResultMessage insert(UserPO po) throws RemoteException {
        return null;
    }

    @Override
    public UserPO get(String id) throws RemoteException {
        return null;
    }

    @Override
    public ResultMessage update(UserPO po) throws RemoteException {
        return null;
    }

    @Override
    public ResultMessage delete(String id) throws RemoteException {
        return null;
    }

    @Override
    public List<UserPO> getAll() throws RemoteException {
        return null;
    }

    @Override
    public TwoTuple<UserPO, LoginReturnState> login(String id, String password) throws RemoteException {
        return null;
    }

}
