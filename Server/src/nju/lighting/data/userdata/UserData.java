package nju.lighting.data.userdata;

import nju.lighting.data.utils.CommonOperation;
import nju.lighting.dataservice.userdataservice.UserDataService;
import nju.lighting.po.user.UserPO;
import shared.Identity;
import shared.LoginReturnState;
import shared.ResultMessage;
import shared.TwoTuple;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 * Created on 2017/11/26.
 * Description: User的数据层模块
 * 11.26日完成，27日重构 通过全部测试
 * @author iznauy
 */
public class UserData extends UnicastRemoteObject implements UserDataService {

    /**
     * 聚合一个CommonOperation
     */
    private CommonOperation<UserPO> commonOperation = null;

    /**
     * 无参构造器
     */
    public UserData() throws RemoteException {
        this.commonOperation = new CommonOperation<>(UserPO.class.getName());
    }


    @Override
    public ResultMessage insert(UserPO po) throws RemoteException {
        return commonOperation.add(po);
    }

    @Override
    public UserPO get(String id) throws RemoteException {
        return commonOperation.getBySingleField("id", id);
    }

    @Override
    public ResultMessage update(UserPO po) throws RemoteException {
        return commonOperation.update(po);
    }

    @Override
    public ResultMessage delete(String id) throws RemoteException {
        return commonOperation.deleteBySingleField("id", id);
    }

    @Override
    public List<UserPO> getAll() throws RemoteException {
        return commonOperation.getAll();
    }

    public List<UserPO> getByIdentity(Identity identity) {
        return commonOperation.getListBySingleField("identity", identity);
    }

    @Override
    public TwoTuple<UserPO, LoginReturnState> login(String id, String password) throws RemoteException {
        UserPO userPO = null;
        LoginReturnState loginReturnState = LoginReturnState.SUCCESS;
        try {
            userPO = get(id);
            if (userPO == null) {
                userPO = null;
                loginReturnState = LoginReturnState.INVALID_USER_NAME;
            } else if (userPO != null && !userPO.getPassword().equals(password)) {
                userPO = null;
                loginReturnState = LoginReturnState.INVALID_PASSWORD;
            }
        } catch (Exception e) {
            e.printStackTrace();
            loginReturnState = LoginReturnState.UNKNOWN;
        }
        return new TwoTuple<>(userPO, loginReturnState);
    }

}
