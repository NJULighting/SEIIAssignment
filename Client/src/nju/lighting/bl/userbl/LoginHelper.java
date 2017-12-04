package nju.lighting.bl.userbl;

import nju.lighting.bl.logbl.Logger;
import nju.lighting.bl.logbl.UserLogger;
import nju.lighting.dataservice.DataFactory;
import nju.lighting.dataservice.userdataservice.UserDataService;
import nju.lighting.po.user.UserPO;
import nju.lighting.vo.UserVO;
import shared.LoginReturnState;
import shared.OPType;
import shared.TwoTuple;

import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.Optional;

/**
 * Created on 2017/11/6.
 * Description:
 * @author Liao
 */
public enum LoginHelper {
    /**
     * Singleton
     */
    INSTANCE;

    private static final String SIGN_IN_MESSAGE = "登录成功";
    private static final String SIGN_OUT_MESSAGE = "登出成功";

    private UserDataService userDataService;
    private User loggedUser; // Store the user
    private Logger logger;

    LoginHelper() {
        try {
            logger = new UserLogger();
            userDataService = DataFactory.getDataBase(UserDataService.class);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    /**
     * Login a user according to the id and password
     * @param id       user's id
     * @param password user's password
     * @return <code>LoginReturnState.INVALID_USER_NAME</code> and null in the <code>TwoTuple</code><br>
     * <code>LoginReturnState.INVALID_PASSWORD</code> and null in the <code>TwoTuple</code><br>
     * <code>LoginReturnState.SUCCESS</code> and a <code>UserVO</code> in the <code>TwoTuple</code>
     */
    public TwoTuple<UserVO, LoginReturnState> login(String id, String password) {
        try {
            TwoTuple<UserPO, LoginReturnState> loginRes = userDataService.login(id, password);
            if (loginRes.r != LoginReturnState.SUCCESS)
                return new TwoTuple<>(null, loginRes.r);

            // Login succeed
            loggedUser = new User(loginRes.t);
            logger.add(OPType.SIGN_IN, SIGN_IN_MESSAGE);
            return new TwoTuple<>(loggedUser.toVO(), loginRes.r);
        } catch (RemoteException e) {
            e.printStackTrace();
            return new TwoTuple<>(null, LoginReturnState.UNKNOWN);
        }
    }


    /**
     * Log out a user, this methods mainly used to log message
     */
    public void logout() {
        Optional.ofNullable(loggedUser).ifPresent(user -> {
            logger.add(OPType.SIGN_OUT, SIGN_OUT_MESSAGE);
            loggedUser = null;
        });
    }

    /**
     * Get the user who signed in
     * @return user signed in
     */
    public User getSignedInUser() {
        return loggedUser;
    }
}
