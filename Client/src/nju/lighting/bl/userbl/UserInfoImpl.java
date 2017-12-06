package nju.lighting.bl.userbl;

import nju.lighting.dataservice.DataFactory;
import nju.lighting.dataservice.userdataservice.UserDataService;
import nju.lighting.po.user.UserPO;

import javax.naming.NamingException;
import java.rmi.RemoteException;

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

    @Override
    public String getNameByID(String userID) {
        try {
            // Get po
            UserDataService dataService = DataFactory.getDataBase(UserDataService.class);
            UserPO po = dataService.get(userID);
            if (po == null)
                return null;

            return po.getName();
        } catch (NamingException | RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getIDOfSignedUser() {
        return LoginHelper.INSTANCE.getSignedInUser().getId();
    }
}
