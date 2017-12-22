package nju.lighting.bl.userbl;

import nju.lighting.blservice.userblservice.UserBLService;
import nju.lighting.vo.UserVO;
import org.junit.Test;
import shared.Identity;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created on 2017/12/1.
 * Description:
 * @author Liao
 */
public class GetUserTest {
    private static final String VALID_ID = "161250068";
    private static final String INVALID_ID = "";
    private static final int USER_LIST_SIZE = 3;

    private UserBLService userBLService = new UserController();

    @Test
    public void getUserListTest() throws Exception {
        List<UserVO> vos = userBLService.getUserList(Identity.FINANCE);
        assertEquals(USER_LIST_SIZE, vos.size());
        assertEquals(VALID_ID, vos.get(2).getID());
    }

    @Test
    public void getUserFailTest() throws Exception {
        UserVO target = userBLService.getUser(INVALID_ID);

        assertNull(target);
    }

    @Test
    public void getUserSuccessfullyTest() throws Exception {
        UserVO target = userBLService.getUser(VALID_ID);

        assertNotNull(target);
        assertEquals(Identity.FINANCE, target.getIdentity());
    }
}
