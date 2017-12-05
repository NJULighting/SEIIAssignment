package nju.lighting.blservice.userblservice;

import nju.lighting.vo.UserVO;
import shared.LoginReturnState;
import shared.TwoTuple;

/**
 * Created on 2017/12/2.
 * Description:
 * @author Liao
 */
public interface LoginService {
    /**
     * Login a user according to the id and password
     * @param id       user's id
     * @param password user's password
     * @return <code>LoginReturnState.INVALID_USER_NAME</code> and null in the <code>TwoTuple</code><br>
     * <code>LoginReturnState.INVALID_PASSWORD</code> and null in the <code>TwoTuple</code><br>
     * <code>LoginReturnState.SUCCESS</code> and a <code>UserVO</code> in the <code>TwoTuple</code>
     */
    TwoTuple<UserVO, LoginReturnState> login(String id, String password);

    /**
     * Logout current signed in user.
     */
    void logout();
}
