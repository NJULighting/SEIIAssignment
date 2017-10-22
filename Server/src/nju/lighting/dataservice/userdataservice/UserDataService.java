package nju.lighting.dataservice.userdataservice;

import nju.lighting.po.ResultMessage;
import nju.lighting.po.UserPO;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created on 2017/10/22.
 * Description:
 * @author Liao
 */
public interface UserDataService {
    void init();

    void finish();

    ResultMessage insert(UserPO po);

    ArrayList<UserPO> find(String keyword);

    UserPO get(String ID);

    ResultMessage update(UserPO po);

    ResultMessage delete(UserPO po);

    ArrayList<UserPO> getAll();

    ResultMessage logIn(UserPO po);

    ResultMessage logOut(UserPO po);
}
