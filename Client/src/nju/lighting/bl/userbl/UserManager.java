package nju.lighting.bl.userbl;

import nju.lighting.bl.logbl.Logger;
import nju.lighting.bl.logbl.MockLogger;
import nju.lighting.dataservice.DataFactory;
import nju.lighting.dataservice.userdataservice.UserDataService;
import nju.lighting.po.user.UserPO;
import nju.lighting.presentation.utils.NameChecker;
import nju.lighting.vo.UserVO;
import shared.Identity;
import shared.ResultMessage;
import shared.UserChangeInfo;

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
     * Get user according to id
     * @param id id of user
     * @return VO object which denote this user, <code>null</code> if id didn't match any user
     */
    public UserVO getUser(String id) {
        try {
            UserPO po = userDataService.get(id);
            if (po == null)
                return null;

            User target = new User(po);
            return target.toVO();
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Add a new user, the id, username, identity, authorized and password mustn't be null
     * @param id         id of the user which should only contains letter or number
     * @param username   user's name which should only contains letter, number or Chinese character
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
            // Check id's correctness
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

    /**
     * Delete a user with the id passed in
     * @param id if of the user
     * @return <code>ResultMessage.SUCCESS</code> as long as the database is in connection<br>
     * or in other words, no failure will be returned unless there's a network problem
     */
    public ResultMessage delete(String id) {
        try {
            return userDataService.delete(id);
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.NETWORK_FAIL;
        }
    }

    public ResultMessage userRenameHimself(String newName) {
        try {
            LoginHelper.INSTANCE.getSignedInUser().rename(newName);
            return ResultMessage.SUCCESS;
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.NETWORK_FAIL;
        }
    }

    public ResultMessage adminChangeUser(String id, UserChangeInfo info) {
        try {
            // Find user
            UserPO po = userDataService.get(id);
            if (po == null) {
                return ResultMessage.INVALID_ID;
            }

            // Change attributes
            User target = new User(po);
            target.changeInfo(info);
            return ResultMessage.SUCCESS;
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.NETWORK_FAIL;
        }
    }
}
