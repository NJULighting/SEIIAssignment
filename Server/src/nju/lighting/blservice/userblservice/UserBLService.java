package nju.lighting.blservice.userblservice;

import nju.lighting.po.ResultMessage;
import nju.lighting.vo.UserVO;

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

    ResultMessage logIn(String username, String password);

    ResultMessage logOut(UserVO vo);
}
