package nju.lighting.bl.userbl;

import nju.lighting.vo.UserVO;
import shared.Identity;

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

    Identity getIdentityById(String userId);

    /**
     * Get current user who's signed in now
     * @return id of the user
     */
    String getIDOfSignedUser();

    UserVO getUserVOByID(String userID);
}
