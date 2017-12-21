package nju.lighting.bl.userbl;

import nju.lighting.blservice.userblservice.UserBLService;
import nju.lighting.vo.UserVO;
import shared.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2017/10/22.
 * Description:
 * @author Liao
 */
@Deprecated
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
    public List<UserVO> findUsers(String keyword) {

        return getUserList();
    }

    @Override
    public UserVO getUser(String id) {
        if (id.equals("0"))
            return new UserVO("Frog 0", "0000", Identity.GENERAL, true);
        else if (id.equals("1"))
            return new UserVO("Frog 1", "0001", Identity.REPOSITORY, false);
        else if(id.equals("2"))
            return new UserVO("Frog 2","0002",Identity.SALE,false);
        else if(id.equals("3"))
            return new UserVO("Frog 3","0003",Identity.SALE_MANAGER,true);
        else if(id.equals("4"))
            return new UserVO("Frog 4","0004",Identity.FINANCE,false);
        else if (id.equals("5"))
            return new UserVO("Admin","0000",Identity.SYSTEM_ADMIN,true);
        return null;
    }

    @Override
    public ResultMessage changeUser(UserChangeInfo changeInfo) {
        return null;
    }

    @Override
    public ResultMessage deleteUser(String id) {
        return id.equals("0") || id.equals("1") || id.equals("2") || id.equals("3") || id.equals("4") ? ResultMessage.SUCCESS : ResultMessage.FAILURE;
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
        }else if(id.equals("jhxs")){
            // Password right
            if(password.equals("1")){
                res.t = getUser("2");
                res.r = LoginReturnState.SUCCESS;
                return res;
            }else {
                // Password wrong
                res.r = LoginReturnState.INVALID_PASSWORD;
                return res;
            }
        }else if(id.equals("xsjl")){
            // Password right
            if(password.equals("1")){
                res.t = getUser("3");
                res.r = LoginReturnState.SUCCESS;
                return res;
            }else {
                // Password wrong
                res.r = LoginReturnState.INVALID_PASSWORD;
                return res;
            }
        }else if(id.equals("cw")){
            // Password right
            if(password.equals("1")){
                res.t = getUser("4");
                res.r = LoginReturnState.SUCCESS;
                return res;
            }else {
                // Password wrong
                res.r = LoginReturnState.INVALID_PASSWORD;
                return res;
            }
        }else if (id.equals("admin")){
            res.t=getUser("5");
            res.r=LoginReturnState.SUCCESS;
            return res;
        }
        // User not existed
        res.r = LoginReturnState.INVALID_USER_NAME;
        return res;
    }

    @Override
    public void logout() {

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
