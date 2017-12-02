package nju.lighting.bl.userbl;

import nju.lighting.bl.utils.NameChecker;
import nju.lighting.blservice.userblservice.UserBLService;
import org.junit.After;
import org.junit.Test;
import shared.Identity;
import shared.ResultMessage;

import static org.junit.Assert.*;

/**
 * Created on 2017/12/1.
 * Description:
 * @author Liao
 */
public class AddUserTest {
    private static final String INVALID_ID = "!@#$1145";
    private static final String DUPLICATED_ID = "161250068";
    private static final String INVALID_NAME = "A@3$JD";
    private static final String ADD_USER_ID = "123456789";
    private static final String ADD_USER_PASSWORD = "Excited";
    private static final String ADD_USER_NAME = "Frog";
    private static final boolean AUTHORIZED = false;

    private UserBLService userBLService = new UserController();

    @Test
    public void nameCheckerTest() throws Exception {
        assertTrue(NameChecker.validName("啊速度加快132LI"));
        assertFalse(NameChecker.validName(INVALID_NAME));
    }

    @Test
    public void succeedTest() throws Exception {
        ResultMessage res = userBLService.addUser(ADD_USER_ID, ADD_USER_PASSWORD, Identity.FINANCE, ADD_USER_NAME, AUTHORIZED);

        assertEquals(ResultMessage.SUCCESS, res);
    }

    @Test
    public void invalidIdTest() throws Exception {
        ResultMessage res = userBLService.addUser(INVALID_ID, ADD_USER_PASSWORD, Identity.FINANCE, ADD_USER_NAME, AUTHORIZED);

        assertEquals(ResultMessage.INVALID_ID, res);
    }

    @Test
    public void invalidNameTest() throws Exception {
        ResultMessage res = userBLService.addUser(ADD_USER_ID, ADD_USER_PASSWORD, Identity.FINANCE, INVALID_NAME, AUTHORIZED);

        assertEquals(ResultMessage.INVALID_NAME, res);
    }

    @Test
    public void duplicationTest() throws Exception {
        ResultMessage res = userBLService.addUser(DUPLICATED_ID, ADD_USER_PASSWORD, Identity.FINANCE, ADD_USER_NAME, AUTHORIZED);

        assertEquals(ResultMessage.DUPLICATE, res);
    }

    @After
    public void tearDown() throws Exception {
        userBLService.deleteUser(ADD_USER_ID);
        userBLService.deleteUser(INVALID_ID);
    }
}