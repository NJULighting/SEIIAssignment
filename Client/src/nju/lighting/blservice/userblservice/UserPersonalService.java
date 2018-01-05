package nju.lighting.blservice.userblservice;

import shared.ResultMessage;

/**
 * Created on 2017/12/2.
 * Description:
 * @author Liao
 */
public interface UserPersonalService {
    /**
     * Change password of a user.
     * @param oldPassword the old password of the user
     * @param newPassword new password of the user
     * @return <code>ResultMessage.SUCCESS</code> if password is not empty<br>
     * <code>ResultMessage.FAILURE</code> if oldPassword is wrong<br>
     * <code>ResultMessage.NETWORK_FAIL</code> if network fails
     */
    ResultMessage userChangePassword(String oldPassword, String newPassword);

    /**
     * Change a user's name.
     * @param newName new name of the user
     * @return <code>ResultMessage.SUCCESS</code> if the name only contains letters, numbers and Chinese characters<br>
     * <code>ResultMessage.NETWORD_FAIL</code> otherwise
     */
    ResultMessage userChangeName(String newName);
}
