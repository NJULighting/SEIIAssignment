package nju.lighting.blservice.userblservice;

import nju.lighting.vo.UserVO;
import shared.Identity;
import shared.ResultMessage;
import shared.UserChangeInfo;

import java.util.List;

/**
 * Created on 2017/12/2.
 * Description:
 * @author Liao
 */
public interface AdministratorService {
    /**
     * Get users of the identity you passed
     * @return User's list
     * @param userIdentity identity of target users
     */
    List<UserVO> getUserList(Identity userIdentity);

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
    ResultMessage addUser(String id, String password, Identity identity, String username, boolean authorized);

    List<UserVO> findUsers(String keyword);

    /**
     * Get user according to id
     * @param id id of user
     * @return VO object which denote this user, <code>null</code> if id didn't match any user
     */
    UserVO getUser(String id);

    /**
     * Change a user's attributes.
     *
     * @param changeInfo A <code>UserChangeInfo</code> object that contains changed information for the user
     * @return <code>ResultMessage.SUCCESS</code> if network works well
     * <code>ResultMessage.FAILURE</code> otherwise
     */
    ResultMessage changeUser(UserChangeInfo changeInfo);

    /**
     * Delete a user with the id passed in
     * @param id if of the user
     * @return <code>ResultMessage.SUCCESS</code> as long as the database is in connection<br>
     * or in other words, no failure will be returned unless there's a network problem
     */
    ResultMessage deleteUser(String id);


}
