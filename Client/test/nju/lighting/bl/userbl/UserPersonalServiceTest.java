package nju.lighting.bl.userbl;

import nju.lighting.blservice.userblservice.UserPersonalService;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import shared.ResultMessage;

/**
 * Created on 2017/12/3.
 * Description:
 * @author Liao
 */
public class UserPersonalServiceTest {
    private static final String USER_ID = "161250068";
    private static final String PASSWORD = "2333";
    private static final String INVALID_PASSWORD = "2312";
    private static final String NEW_NAME = "Excited";
    private static final String NEW_PASSWORD = "123456";

    private UserPersonalService personalService = new UserController();

    @Before
    public void setUp() throws Exception {
        LoginHelper.INSTANCE.login(USER_ID, PASSWORD);
    }

    @Test
    public void changeNameTest() throws Exception {
        User user = LoginHelper.INSTANCE.getSignedInUser();
        String oldName = user.getName();

        ResultMessage res = personalService.userChangeName(NEW_NAME);

        assertEquals(NEW_NAME, user.getName());
        assertEquals(ResultMessage.SUCCESS, res);

        personalService.userChangeName(oldName);
    }

    @Test
    public void changePasswordFailTest() throws Exception {
        ResultMessage res = personalService.userChangePassword(INVALID_PASSWORD, NEW_PASSWORD);

        assertEquals(ResultMessage.FAILURE, res);
    }

    @Test
    public void changePasswordSuccessfullyTest() throws Exception {
        User user = LoginHelper.INSTANCE.getSignedInUser();

        ResultMessage res = personalService.userChangePassword(PASSWORD, NEW_PASSWORD);

        assertTrue(user.passwordRight(NEW_PASSWORD));
        assertFalse(user.passwordRight(PASSWORD));

        personalService.userChangePassword(NEW_PASSWORD, PASSWORD);
    }
}
