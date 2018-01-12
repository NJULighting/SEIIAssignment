package nju.lighting.data.userdata;

import nju.lighting.dataservice.userdataservice.UserDataService;
import nju.lighting.po.user.UserPO;
import shared.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * Created on 2017/10/22.
 * Description:
 * @author Liao
 */

@Deprecated
public class UserDataService_Stub extends UnicastRemoteObject implements UserDataService {

    public UserDataService_Stub() throws RemoteException {
    }

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
        return id.equals("233") ? ResultMessage.FAILURE : ResultMessage.SUCCESS;
    }

    @Override
    public ArrayList<UserPO> getAll() {
        UserPO po = new UserPO("Frog", "Excited", "0000", Identity.GENERAL, false);
        ArrayList<UserPO> arrayList = new ArrayList<>();
        arrayList.add(po);
        return arrayList;
    }

    @Override
    public TwoTuple<UserPO, LoginReturnState> login(String id, String password) throws RemoteException {
        return new TwoTuple<>(null, LoginReturnState.INVALID_PASSWORD);
    }
}
