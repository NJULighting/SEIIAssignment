package nju.lighting.bl.userbl;

import nju.lighting.blservice.userblservice.UserBLService;
import nju.lighting.vo.UserVO;
import org.junit.Test;
import shared.LoginReturnState;
import shared.TwoTuple;

import static org.junit.Assert.*;

/**
 * Created on 2017/11/29.
 * Description:
 * @author Liao
 */
public class UserControllerTest {
    UserBLService userBLService = new UserController();

    @Test
    public void loginInvalidIDTest() throws Exception {
        TwoTuple<UserVO, LoginReturnState> res = userBLService.login("", "Excited");
        assertNull(res.t);
        assertEquals(LoginReturnState.INVALID_USER_NAME, res.r);
    }

    @Test
    public void loginInvalidPasswordTest() throws Exception {
        TwoTuple<UserVO, LoginReturnState> res = userBLService.login("000", "NotExcited");
        assertNull(res.t);
        assertEquals(LoginReturnState.INVALID_PASSWORD, res.r);
    }

    @Test
    public void loginSuccessfullyTest() throws Exception {
        TwoTuple<UserVO, LoginReturnState> res = userBLService.login("0000", "Excited");
        assertNotNull(res.t);
        assertEquals(LoginReturnState.SUCCESS, res.r);
    }
}