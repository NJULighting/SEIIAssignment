package shared;

import nju.lighting.dataservice.DataFactory;
import nju.lighting.dataservice.userdataservice.UserDataService;
import nju.lighting.po.user.UserPO;

import javax.naming.NamingException;
import java.rmi.RemoteException;

/**
 * Created on 2017/12/2.
 * Description:
 * @author Liao
 */
public class UserChangeInfo {

    public final String name;
    public final String password;
    public final Identity identity;
    public final boolean authorized;

    private UserChangeInfo(Builder builder) {
        name = builder.name;
        password = builder.password;
        identity = builder.identity;
        authorized = builder.authorized;
    }

    public static class Builder {
        // Optional parameters
        private String name;
        private String password;
        private Identity identity;
        private boolean authorized;

        public Builder(String userID) throws RemoteException {
            // Get user's current information
            try {
                UserDataService userDataService = DataFactory.getDataBase(UserDataService.class);
                UserPO po = userDataService.get(userID);
                // Set default attributes
                name = po.getName();
                password = po.getPassword();
                identity = po.getIdentity();
                authorized = po.getAuthorized();
            } catch (NamingException e) {
                e.printStackTrace();
            }
        }

        public Builder rename(String name) {
            this.name = name;
            return this;
        }

        public Builder changePassword(String password) {
            this.password = password;
            return this;
        }

        public Builder changeIdentity(Identity identity) {
            this.identity = identity;
            return this;
        }

        public Builder changeAuthorized(boolean authorized) {
            this.authorized = authorized;
            return this;
        }

        public UserChangeInfo build() {
            return new UserChangeInfo(this);
        }
    }
}
