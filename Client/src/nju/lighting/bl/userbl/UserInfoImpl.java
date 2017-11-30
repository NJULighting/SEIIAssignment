package nju.lighting.bl.userbl;

/**
 * Created on 2017/11/30.
 * Description:
 * @author Liao
 */
public class UserInfoImpl implements UserInfo {
    @Override
    public boolean authorized() {
        return LoginHelper.INSTANCE.getSignedInUser().isAuthorized();
    }
}
