package nju.lighting.blservice.userblservice;

import nju.lighting.vo.UserVO;
import shared.LoginReturnState;
import shared.ResultMessage;
import shared.TwoTuple;

import java.util.ArrayList;

/**
 * Created on 2017/10/21.
 * Description:
 * @author Liao
 */
public interface UserBLService {
    ArrayList<UserVO> getUserList();

    ResultMessage addUser(UserVO vo);

    ArrayList<UserVO> findUsers(String keyword);

    UserVO getUser(String id);

    ResultMessage deleteUser(String id);

    ResultMessage modifyUser(UserVO vo);

    String generateJobNum(UserVO vo);

    // id 是登录者的工号
    TwoTuple<UserVO, LoginReturnState> login(String id, String password);

    ResultMessage logOut(UserVO vo);
}
