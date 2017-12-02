package nju.lighting.blservice.userblservice;

import shared.ResultMessage;

/**
 * Created on 2017/12/2.
 * Description:
 * @author Liao
 */
public interface UserPersonalService {
    /**
     * Change password of a user. This method should only be invoked by the user himself
     * @param oldPassword the old password of the user
     * @param newPassword new password of the user
     * @return <code>ResultMessage.SUCCESS</code> if password is not empty<br>
     *     <code>ResultMessage.FAILURE</code> otherwise
     */
    ResultMessage userChangePassword(String oldPassword, String newPassword);

    /**
     * Change a user's name. This method should only be used when the user himself want to change his name
     * @param newName new name of the user
     * @return <code>ResultMessage.SUCCESS</code> if the name only contains letters, numbers and Chinese characters<br>
     * <code>ResultMessage.FAILURE</code> otherwise
     */
    ResultMessage changeName(String newName);
}
