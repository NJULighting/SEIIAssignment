package nju.lighting.bl.utls;

import nju.lighting.bl.userbl.LoginHelper;

/**
 * Created on 2017/12/5.
 * Description:
 * @author Liao
 */
public class LoginTestHelper {
    private static final String AUTHORIZED_USER = "161250068";
    private static final String NOT_AUTHORIZED_USER = "1611200xx";
    private static final String AUTHORIZED_USER_PASSWORD = "2333";
    private static final String NOT_AUTHORIZED_USER_PASSWORD = "9999";

    private static LoginHelper loginHelper = LoginHelper.INSTANCE;

    public static void loginAuthorizedUser() {
        loginHelper.logout();
        loginHelper.login(AUTHORIZED_USER, AUTHORIZED_USER_PASSWORD);
    }

    public static void loginNotAuthorizedUser() {
        loginHelper.logout();;
        loginHelper.login(NOT_AUTHORIZED_USER, NOT_AUTHORIZED_USER_PASSWORD);
    }
}
