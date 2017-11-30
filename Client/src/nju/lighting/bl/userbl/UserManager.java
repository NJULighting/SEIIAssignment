package nju.lighting.bl.userbl;

import nju.lighting.bl.logbl.Logger;
import nju.lighting.bl.logbl.MockLogger;
import nju.lighting.bl.utils.NameChecker;
import nju.lighting.dataservice.DataFactory;
import nju.lighting.dataservice.userdataservice.UserDataService;
import nju.lighting.po.user.UserPO;
import nju.lighting.vo.UserVO;
import shared.Identity;
import shared.ResultMessage;

import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2017/11/6.
 * Description:
 * @author Liao
 */
public enum UserManager {
    INSTANCE;

    private UserDataService userDataService;
    private Logger logger;

    UserManager() {
        try {
            userDataService = DataFactory.getDataBase(UserDataService.class);
            logger = new MockLogger(); // TODO: 2017/11/30 Change logger here
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get all users
     * @return User's list
     */
    public ArrayList<UserVO> getUserList() {
        try {
            List<UserPO> userPOs = userDataService.getAll();
            ArrayList<UserVO> userVOS = new ArrayList<>();
            userPOs.forEach(userPO -> userVOS.add(new User(userPO).toVO()));
            return userVOS;
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Add a new user, the id, username, identity, authorized and password mustn't be null
     * @param id         id of the user
     * @param username   user's name
     * @param identity   user's identity
     * @param authorized this user whether be authorized
     * @param password   user's password
     * @return <code>ResultMessage.INVALID_ID</code> if id is incorrect<br>
     * <code>ResultMessage.INVALID_NAME</code> if name is incorrect<br>
     * <code>ResultMessage.DUPLICATE</code> if id repeats with others<br>
     * <code>ResultMessage.SUCCESS</code> if add successfully<br>
     * <code>ResultMessage.NETWORK_FAIL</code> if RemoteException was caught
     */
    public ResultMessage addUser(String id, String username, Identity identity, boolean authorized, String password) {
        try {
            // Check id correctness
            if (!NameChecker.validID(id))
                return ResultMessage.INVALID_ID;

            // Check name
            if (!NameChecker.validName(username))
                return ResultMessage.INVALID_NAME;

            // Try to add the user to database
            User user = new User(username, password, id, identity, authorized);
            ResultMessage res = userDataService.insert(user.toPO());
            if (res == ResultMessage.FAILURE) // Duplicated id
                return ResultMessage.DUPLICATE;
            else return res; // SUCCESS
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.NETWORK_FAIL;
        }
    }
}
