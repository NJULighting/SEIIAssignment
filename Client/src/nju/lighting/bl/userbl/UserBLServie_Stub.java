package nju.lighting.bl.userbl;

import nju.lighting.blservice.userblservice.UserBLService;
import shared.Identity;
import nju.lighting.vo.UserVO;
import shared.ResultMessage;

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
    public ResultMessage addUser(UserVO vo) {
        return vo == null ? ResultMessage.FAILURE : ResultMessage.SUCCESS;
    }

    @Override
    public ArrayList<UserVO> findUsers(String keyword) {

        return getUserList();
    }

    @Override
    public UserVO getUser(String id) {
        if (id.equals("0"))
            return new UserVO("Frog 0", "Excited", "0000", Identity.GENERAL.toString(), true);
        else if (id.equals("1"))
            return new UserVO("Frog 1", "Naive", "0001", Identity.REPOSITORY.toString(), false);
        return null;
    }

    @Override
    public ResultMessage deleteUser(String id) {
        return id.equals("0") || id.equals("1") ? ResultMessage.SUCCESS : ResultMessage.FAILURE;
    }

    @Override
    public ResultMessage modifyUser(UserVO vo) {
        if (vo.getUsername().contains("Frog"))
            return ResultMessage.SUCCESS;
        return ResultMessage.FAILURE;
    }

    @Override
    public String generateJobNum(UserVO vo) {
        return "1234567890";
    }

    @Override
    public ResultMessage login(String id, String password) {
        if ((id.equals("Frog 0") && password.equals("Excited"))
                || (id.equals("Frog1") && password.equals("Naive")))
            return ResultMessage.SUCCESS;
        return ResultMessage.FAILURE;
    }

    @Override
    public ResultMessage logOut(UserVO vo) {
        return vo == null ? ResultMessage.FAILURE : ResultMessage.SUCCESS;
    }
}
