package nju.lighting.bl.userbl;

import nju.lighting.bl.logbl.Logger;
import nju.lighting.bl.logbl.MockLogger;
import nju.lighting.dataservice.DataFactory;
import nju.lighting.dataservice.userdataservice.UserDataService;
import nju.lighting.po.user.UserPO;
import nju.lighting.vo.UserVO;
import shared.LoginReturnState;
import shared.ResultMessage;
import shared.TwoTuple;

import javax.naming.NamingException;
import java.rmi.RemoteException;

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

    private UserDataService userDataService;
    private User loggedUser; // Store the user
    private Logger logger;

    LoginHelper() {
        try {
            userDataService = DataFactory.getDataBase(UserDataService.class);
            logger = new MockLogger();
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
            // TODO: 2017/11/30 Add log here
            return new TwoTuple<>(loggedUser.toVO(), loginRes.r);
        } catch (RemoteException e) {
            e.printStackTrace();
            return new TwoTuple<>(null, LoginReturnState.UNKNOWN);
        }
    }

    /**
     * Get the user who signed in
     * @return user signed in
     */
    public User getSignedInUser() {
        return loggedUser;
    }
}
