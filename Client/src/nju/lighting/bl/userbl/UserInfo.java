package nju.lighting.bl.userbl;

import nju.lighting.vo.UserVO;

/**
 * Created on 2017/11/14.
 * Description:
 * @author Liao
 */
public interface UserInfo {
    /**
     * Check current user's authority
     * @return true if user is authorized, false otherwise
     */
    boolean authorized();

    /**
     * Get user's name by passing id of this user
     * @param userID id of the user
     * @return name of the user if id is valid, <code>null</code> otherwise
     */
    String getNameByID(String userID);

    /**
     * Get current user who's signed in now
     * @return id of the user
     */
    String getIDOfSignedUser();

    UserVO getUserVOByID(String userID);
}
