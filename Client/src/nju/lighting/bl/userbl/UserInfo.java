package nju.lighting.bl.userbl;

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
}
