package nju.lighting.bl.userbl;

import nju.lighting.blservice.userblservice.LoginService;
import nju.lighting.blservice.userblservice.UserBLService;
import nju.lighting.vo.UserVO;
import org.junit.Test;
import shared.LoginReturnState;
import shared.TwoTuple;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created on 2017/11/29.
 * Description:
 * @author Liao
 */
public class LoginTest {
    private static final String USER_ID = "161250068";
    private static final String WRONG_ID = "1111111";
    private static final String RIGHT_PASSWORD = "2333";
    private static final String WRONG_PASSWORD = "Excited";

    private LoginService userBLService = new UserController();

    @Test
    public void loginInvalidIDTest() throws Exception {
        TwoTuple<UserVO, LoginReturnState> res = userBLService.login(WRONG_ID, RIGHT_PASSWORD);

        assertNull(res.t);
        assertEquals(LoginReturnState.INVALID_USER_NAME, res.r);
    }

    @Test
    public void loginInvalidPasswordTest() throws Exception {
        TwoTuple<UserVO, LoginReturnState> res = userBLService.login(USER_ID, WRONG_PASSWORD);

        assertNull(res.t);
        assertEquals(LoginReturnState.INVALID_PASSWORD, res.r);
    }

    @Test
    public void loginSuccessfullyTest() throws Exception {
        TwoTuple<UserVO, LoginReturnState> res = userBLService.login(USER_ID, RIGHT_PASSWORD);

        assertNotNull(res.t);
        assertEquals(LoginReturnState.SUCCESS, res.r);
    }

    @Test
    public void logoutTest() throws Exception {
        userBLService.login(USER_ID, RIGHT_PASSWORD);
        userBLService.logout();
    }
}