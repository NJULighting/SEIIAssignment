package nju.lighting.bl.userbl;

/**
 * Created on 2017/12/5.
 * Description:
 * @author Liao
 */
public class LoginTestHelper {
    public static final String AUTHORIZED_USER = "161250068";
    private static final String NOT_AUTHORIZED_USER = "161120030";
    private static final String AUTHORIZED_USER_PASSWORD = "1";
    private static final String NOT_AUTHORIZED_USER_PASSWORD = "1";

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
