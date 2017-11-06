package nju.lighting.data.userdata;

import nju.lighting.dataservice.userdataservice.UserDataService;
import shared.Identity;
import nju.lighting.po.ResultMessage;
import nju.lighting.po.UserPO;

import java.util.ArrayList;

/**
 * Created on 2017/10/22.
 * Description:
 * @author Liao
 */
public class UserDataService_Stub implements UserDataService {
    @Override
    public void init() {
        System.out.println("init");
    }

    @Override
    public void finish() {
        System.out.println("finish");
    }

    @Override
    public ResultMessage insert(UserPO po) {
        return po == null ? ResultMessage.FAILURE : ResultMessage.SUCCESS;
    }

    @Override
    public ArrayList<UserPO> find(String keyword) {
        UserPO frog = new UserPO("Frog", "Excited", "0000", Identity.GENERAL);
        UserPO journalist = new UserPO("Journalist", "Naive", "0001", Identity.SALE);
        ArrayList<UserPO> users = new ArrayList<>();
        users.add(frog);
        users.add(journalist);
        return keyword.isEmpty() ? null : users;
    }

    @Override
    public UserPO get(String ID) {
        return ID.isEmpty() ? null : new UserPO("Frog", "Excited", "0000", Identity.GENERAL);
    }

    @Override
    public ResultMessage update(UserPO po) {
        return po == null ? ResultMessage.FAILURE : ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage delete(UserPO po) {
        return po == null ? ResultMessage.FAILURE : ResultMessage.SUCCESS;
    }

    @Override
    public ArrayList<UserPO> getAll() {
        return null;
    }

    @Override
    public ResultMessage logIn(UserPO po) {
        return po == null ? ResultMessage.FAILURE : ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage logOut(UserPO po) {
        return po == null ? ResultMessage.FAILURE : ResultMessage.SUCCESS;
    }
}
