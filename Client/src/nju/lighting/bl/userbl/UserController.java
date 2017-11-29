package nju.lighting.bl.userbl;

import nju.lighting.blservice.userblservice.UserBLService;
import nju.lighting.vo.UserVO;
import shared.LoginReturnState;
import shared.ResultMessage;
import shared.TwoTuple;

import java.util.ArrayList;

/**
 * Created on 2017/11/6.
 * Description:
 * @author Liao
 */
public class UserController implements UserBLService{
    private LoginHelper loginHelper = LoginHelper.INSTANCE;

    @Override
    public ArrayList<UserVO> getUserList() {
        return null;
    }

    @Override
    public ResultMessage addUser(UserVO vo) {
        return null;
    }

    @Override
    public ArrayList<UserVO> findUsers(String keyword) {
        return null;
    }

    @Override
    public UserVO getUser(String id) {
        return null;
    }

    @Override
    public ResultMessage deleteUser(String id) {
        return null;
    }

    @Override
    public ResultMessage modifyUser(UserVO vo) {
        return null;
    }

    @Override
    public String generateJobNum(UserVO vo) {
        return null;
    }

    @Override
    public TwoTuple<UserVO, LoginReturnState> login(String id, String password) {
        return loginHelper.login(id, password);
    }

    @Override
    public ResultMessage logOut(UserVO vo) {
        return null;
    }
}
