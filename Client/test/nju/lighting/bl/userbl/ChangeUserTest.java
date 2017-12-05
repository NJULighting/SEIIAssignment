package nju.lighting.bl.userbl;

import nju.lighting.blservice.userblservice.AdministratorService;
import nju.lighting.blservice.userblservice.LoginService;
import nju.lighting.dataservice.DataFactory;
import nju.lighting.dataservice.userdataservice.UserDataService;
import nju.lighting.po.user.UserPO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import shared.Identity;
import shared.ResultMessage;
import shared.UserChangeInfo;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

/**
 * Created on 2017/12/3.
 * Description:
 * @author Liao
 */
public class ChangeUserTest {
    private static final String USER_ID = "161250068";
    private static final String INVALID_USER_ID = "1612500xx";
    private static final String PASSWORD = "2333";
    private static final String NEW_NAME = "ExcitedFrog";
    private static final String NEW_PASSWORD = "1234567";
    private static final Identity NEW_IDENTITY = Identity.GENERAL;
    private static final boolean NEW_AUTHORITY = false;

    private AdministratorService adminService = new UserController();
    private UserDataService dataService = DataFactory.getDataBase(UserDataService.class);
    private UserPO oldData = dataService.get(USER_ID);
    private UserChangeInfo.Builder builder;

    public ChangeUserTest() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        builder = new UserChangeInfo.Builder(USER_ID);
        LoginHelper.INSTANCE.login(USER_ID, PASSWORD);
    }

    @Test(expected = NoSuchElementException.class)
    public void invalidIDTest() throws Exception {
        builder = new UserChangeInfo.Builder(INVALID_USER_ID);
    }

    @Test
    public void changeNameTest() throws Exception {
        builder.rename(NEW_NAME);

        adminService.changeUser(builder.build());

        UserPO po = dataService.get(USER_ID);
        assertEquals(NEW_NAME, po.getName());
    }

    @Test
    public void changePasswordTest() throws Exception {
        builder.changePassword(NEW_PASSWORD);

        adminService.changeUser(builder.build());

        UserPO po = dataService.get(USER_ID);
        assertEquals(NEW_PASSWORD, po.getPassword());
    }

    @Test
    public void changeIdentity() throws Exception {
        builder.changeIdentity(NEW_IDENTITY);

        adminService.changeUser(builder.build());

        UserPO po = dataService.get(USER_ID);
        assertEquals(NEW_IDENTITY, po.getIdentity());
    }

    @Test
    public void changeAuthority() throws Exception {
        builder.changeAuthorized(NEW_AUTHORITY);

        adminService.changeUser(builder.build());

        UserPO po = dataService.get(USER_ID);
        assertFalse(po.getAuthorized());
    }

    @Test
    public void changeTogetherTest() throws Exception {
        builder.changeAuthorized(NEW_AUTHORITY).changeIdentity(NEW_IDENTITY)
                .changePassword(NEW_PASSWORD).rename(NEW_NAME);

        ResultMessage res = adminService.changeUser(builder.build());

        UserPO po = dataService.get(USER_ID);
        assertEquals(ResultMessage.SUCCESS, res);
        assertFalse(po.getAuthorized());
        assertEquals(NEW_PASSWORD, po.getPassword());
        assertEquals(NEW_IDENTITY, po.getIdentity());
        assertEquals(NEW_NAME, po.getName());
    }

    @After
    public void tearDown() throws Exception {
        UserChangeInfo.Builder builder = new UserChangeInfo.Builder(USER_ID);
        builder.rename(oldData.getName());
        builder.changeAuthorized(oldData.getAuthorized());
        builder.changeIdentity(oldData.getIdentity());
        builder.changePassword(oldData.getPassword());
        adminService.changeUser(builder.build());
    }
}
