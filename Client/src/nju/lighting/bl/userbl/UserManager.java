package nju.lighting.bl.userbl;

import nju.lighting.bl.logbl.Logger;
import nju.lighting.bl.logbl.UserLogger;
import nju.lighting.bl.utils.DataServiceFunction;
import nju.lighting.bl.utils.DataServiceSupplier;
import nju.lighting.dataservice.DataFactory;
import nju.lighting.dataservice.userdataservice.UserDataService;
import nju.lighting.po.user.UserPO;
import nju.lighting.presentation.utils.NameChecker;
import nju.lighting.vo.UserVO;
import shared.Identity;
import shared.OPType;
import shared.ResultMessage;
import shared.UserChangeInfo;

import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created on 2017/11/6.
 * Description:
 * @author Liao
 */
enum UserManager {
    INSTANCE;

    private UserDataService userDataService;
    private Logger logger;

    UserManager() {
        try {
            userDataService = DataFactory.getDataBase(UserDataService.class);
            logger = new UserLogger();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get all users
     * @return User's list
     */
    public List<UserVO> getUserList() {
        return DataServiceSupplier.getAll(userDataService::getAll, po -> new User(po).toVO());
    }

    /**
     * Get user according to id
     * @param id id of user
     * @return VO object which denote this user, <code>null</code> if id didn't match any user
     */
    public UserVO getUser(String id) {
        return DataServiceFunction.findByToEntity(id, userDataService::get, userPO -> new User(userPO).toVO());
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
            else {
                logger.add(OPType.ADD, "添加新用户 " + id + " " + username);
                return res; // SUCCESS
            }
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
            logger.add(OPType.DELETE, "删除用户 " + id);
            return userDataService.delete(id);
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.NETWORK_FAIL;
        }
    }

    /**
     * Change a user's name.
     * @param newName new name of the user
     * @return <code>ResultMessage.SUCCESS</code> if the name only contains letters, numbers and Chinese characters<br>
     * <code>ResultMessage.NETWORK_FAIL</code> otherwise
     */
    public ResultMessage userRenameHimself(String newName) {
        try {
            LoginHelper.INSTANCE.getSignedInUser().rename(newName);
            logger.add(OPType.MODIFY, "重命名为 " + newName);
            return ResultMessage.SUCCESS;
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.NETWORK_FAIL;
        }
    }

    /**
     * Change password of a user.
     * @param oldPassword the old password of the user
     * @param newPassword new password of the user
     * @return <code>ResultMessage.SUCCESS</code> if password is not empty<br>
     * <code>ResultMessage.FAILURE</code> if oldPassword is wrong<br>
     * <code>ResultMessage.NETWORK_FAIL</code> if network fails
     */
    public ResultMessage userChangePassword(String oldPassword, String newPassword) {
        User user = LoginHelper.INSTANCE.getSignedInUser();
        if (!user.passwordRight(oldPassword)) {
            return ResultMessage.FAILURE;
        }

        // Change password
        try {
            user.changePassword(newPassword);
            logger.add(OPType.MODIFY, "修改密码");
            return ResultMessage.SUCCESS;
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.NETWORK_FAIL;
        }
    }

    /**
     * Change a user's attributes.
     * @param id   id of the target user
     * @param info A <code>UserChangeInfo</code> object that contains changed information for the user
     * @return <code>ResultMessage.SUCCESS</code> if network works well
     * <code>ResultMessage.FAILURE</code> otherwise
     */
    public ResultMessage adminChangeUser(UserChangeInfo info) {
        try {
            // Find user
            UserPO po = userDataService.get(info.id);
            if (po == null) {
                return ResultMessage.INVALID_ID;
            }
            // TODO: 2017/12/5 Refactor here
            // Change attributes
            User target = new User(po);
            target.changeInfo(info);
            logger.add(OPType.MODIFY, "管理员修改用户" + info.id + " 详细信息：" + info.toString());
            return ResultMessage.SUCCESS;
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.NETWORK_FAIL;
        }
    }
}
