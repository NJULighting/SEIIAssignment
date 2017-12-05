package nju.lighting.bl.userbl;

import nju.lighting.blservice.userblservice.UserBLService;
import nju.lighting.vo.UserVO;
import shared.*;

import java.util.ArrayList;

/**
 * Created on 2017/11/6.
 * Description:
 * @author Liao
 */
public class UserController implements UserBLService{
    private LoginHelper loginHelper = LoginHelper.INSTANCE;
    private UserManager userManager = UserManager.INSTANCE;

    @Override
    public ArrayList<UserVO> getUserList() {
        return userManager.getUserList();
    }

    @Override
    public ResultMessage addUser(String id, String password, Identity identity, String username, boolean authorized) {
        return userManager.addUser(id, username, identity, authorized, password);
    }

    @Override
    public ArrayList<UserVO> findUsers(String keyword) {
        return null;// TODO: 2017/12/3 complete this method
    }

    @Override
    public UserVO getUser(String id) {
        return userManager.getUser(id);
    }

    @Override
    public ResultMessage changeUser(UserChangeInfo changeInfo) {
        return userManager.adminChangeUser(changeInfo);
    }

    @Override
    public ResultMessage deleteUser(String id) {
        return userManager.delete(id);
    }

    @Override
    public TwoTuple<UserVO, LoginReturnState> login(String id, String password) {
        return loginHelper.login(id, password);
    }

    @Override
    public void logout() {
        loginHelper.logout();
    }

    @Override
    public ResultMessage userChangePassword(String oldPassword, String newPassword) {
        return userManager.userChangePassword(oldPassword, newPassword);
    }

    @Override
    public ResultMessage userChangeName(String newName) {
        return userManager.userRenameHimself(newName);
    }
}
