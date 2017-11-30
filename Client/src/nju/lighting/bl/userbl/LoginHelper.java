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
            // Check id
            UserPO po = userDataService.get(id);
            if (po == null)
                return new TwoTuple<>(null, LoginReturnState.INVALID_USER_NAME);
            // Check password
            User user = new User(po);
            if (user.passwordRight(password)) {
                return new TwoTuple<>(user.toVO(), LoginReturnState.SUCCESS);
                // TODO: 2017/11/30 Log here
            }
            else
                return new TwoTuple<>(null, LoginReturnState.INVALID_PASSWORD);
        } catch (RemoteException e) {
            e.printStackTrace();
            return new TwoTuple<>(null, LoginReturnState.UNKNOWN);
        }
    }
}
