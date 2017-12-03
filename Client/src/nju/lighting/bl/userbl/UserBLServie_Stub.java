package nju.lighting.bl.userbl;

import nju.lighting.blservice.userblservice.UserBLService;
import nju.lighting.vo.UserVO;
import shared.*;

import java.util.ArrayList;

/**
 * Created on 2017/10/22.
 * Description:
 * @author Liao
 */
public class UserBLServie_Stub implements UserBLService {

    @Override
    public ArrayList<UserVO> getUserList() {
        ArrayList<UserVO> userList = new ArrayList<>();
        userList.add(getUser("0"));
        userList.add(getUser("1"));
        return userList;
    }

    @Override
    public ResultMessage addUser(String id, String password, Identity identity, String username, boolean authorized) {
        return password.isEmpty() ? ResultMessage.FAILURE : ResultMessage.SUCCESS;
    }

    @Override
    public ArrayList<UserVO> findUsers(String keyword) {

        return getUserList();
    }

    @Override
    public UserVO getUser(String id) {
        if (id.equals("0"))
            return new UserVO("Frog 0", "0000", Identity.GENERAL, true);
        else if (id.equals("1"))
            return new UserVO("Frog 1", "0001", Identity.REPOSITORY, false);
        return null;
    }

    @Override
    public ResultMessage changeUser(String id, UserChangeInfo changeInfo) {
        return null;
    }

    @Override
    public ResultMessage deleteUser(String id) {
        return id.equals("0") || id.equals("1") ? ResultMessage.SUCCESS : ResultMessage.FAILURE;
    }

    @Override
    public TwoTuple<UserVO, LoginReturnState> login(String id, String password) {
        TwoTuple<UserVO, LoginReturnState> res = new TwoTuple<>();
        if (id.equals("zjl")) {
            // Password right
            if (password.equals("1")) {
                res.t = getUser("0");
                res.r = LoginReturnState.SUCCESS;
                return res;
            } else {
                // Password wrong
                res.r = LoginReturnState.INVALID_PASSWORD;
                return res;
            }
        }else if(id.equals("kc")){
            // Password right
            if (password.equals("1")) {
                res.t = getUser("1");
                res.r = LoginReturnState.SUCCESS;
                return res;
            } else {
                // Password wrong
                res.r = LoginReturnState.INVALID_PASSWORD;
                return res;
            }
        }
        // User not existed
        res.r = LoginReturnState.INVALID_USER_NAME;
        return res;
    }

    @Override
    public ResultMessage userChangePassword(String oldPassword, String newPassword) {
        return null;
    }

    @Override
    public ResultMessage userChangeName(String newName) {
        return null;
    }
}
