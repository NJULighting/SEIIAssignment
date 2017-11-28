package nju.lighting.data.userdata;

import nju.lighting.dataservice.userdataservice.UserDataService;
import nju.lighting.po.user.UserPO;
import shared.Identity;
import shared.LoginReturnState;
import shared.ResultMessage;
import shared.TwoTuple;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created on 2017/10/22.
 * Description:
 * @author Liao
 */
public class UserDataService_Stub implements UserDataService {

    @Override
    public ResultMessage insert(UserPO po) {
        return po == null ? ResultMessage.FAILURE : ResultMessage.SUCCESS;
    }

    @Override
    public UserPO get(String ID) {
        return ID.isEmpty() ? null : new UserPO("Frog", "Excited", "0000", Identity.GENERAL, false);
    }

    @Override
    public ResultMessage update(UserPO po) {
        return po == null ? ResultMessage.FAILURE : ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage delete(String id) throws RemoteException {
        return null;
    }

    @Override
    public ArrayList<UserPO> getAll() {
        return null;
    }

    @Override
    public TwoTuple<UserPO, LoginReturnState> login(String id, String password) throws RemoteException {
        return null;
    }
}
