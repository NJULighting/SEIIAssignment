package nju.lighting.blservice.documentblservice;

import nju.lighting.vo.UserVO;

import java.util.ArrayList;

/**
 * Created on 2017/10/21.
 * Description:
 * @author Liao
 */
public interface UserBLService {
    ArrayList<UserVO> getUserList();

    void addUser(UserVO vo);

    ArrayList<UserVO> findUser(String keyword);

    UserVO getUser(String id);

    void deleteUser(String id);

    void modifyUser(UserVO vo);

    String generateJobNum(UserVO vo);


}
