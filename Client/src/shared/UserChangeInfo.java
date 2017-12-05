package shared;

import nju.lighting.dataservice.DataFactory;
import nju.lighting.dataservice.userdataservice.UserDataService;
import nju.lighting.po.user.UserPO;

import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

/**
 * Created on 2017/12/2.
 * Description: Store change information for a user<br>
 * Sample code:<br>
 * <code>UserChangeInfo.Builder builder = new UserChangeInfo.Builder(userID);</code><br>
 * <code>builder.rename(name).changePassword(password).changeIdentity(identity).changeAuthority(authority)</code><br>
 * <code>UserChangeInfo info = builder.build()</code><br>
 * <b>注：可以不用if或者switch，直接调用全部方法，哪怕该属性没有修改,该方法会自动跳过没有修改的属性。</b>当然也可以只调用需要的方法<br>
 * @author Liao
 */
public class UserChangeInfo {

    public final String id;
    public final String name;
    public final String password;
    public final Identity identity;
    public final boolean authorized;
    private final String description;



    private UserChangeInfo(Builder builder) {
        id = builder.id;
        name = builder.name;
        password = builder.password;
        identity = builder.identity;
        authorized = builder.authorized;
        description = builder.description;
    }

    public static class Builder {
        // Optional parameters
        private String id;
        private String name;
        private String password;
        private Identity identity;
        private boolean authorized;
        private String description = "";

        /**
         * Builder pattern to create <code>UserChangeInfo</code>（感觉好像有点多余QAQ）
         * @param userID id of the user
         * @throws RemoteException if network fail
         * @throws NoSuchElementException if this id matches no user
         */
        public Builder(String userID) throws RemoteException {
            id = userID;
            // Get user's current information
            try {
                UserDataService userDataService = DataFactory.getDataBase(UserDataService.class);
                UserPO po = userDataService.get(userID);
                if (po == null)
                    throw new NoSuchElementException("No such user");
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
            if (this.name.equals("name")) {
                return this;
            }
            this.name = name;
            description += "改名为 " + name + ";";
            return this;
        }

        public Builder changePassword(String password) {
            if (this.name.equals(password))
                return this;
            this.password = password;
            description += "改密码为 " + password + ";";
            return this;
        }

        public Builder changeIdentity(Identity identity) {
            if (this.identity == identity)
                return this;
            this.identity = identity;
            description += "改身份为 " + identity + ";";
            return this;
        }

        public Builder changeAuthorized(boolean authorized) {
            if (authorized == this.authorized)
                return this;
            this.authorized = authorized;
            description += "改权限为 " + authorized + ";";
            return this;
        }

        public UserChangeInfo build() {
            return new UserChangeInfo(this);
        }
    }

    @Override
    public String toString() {
        return description;
    }
}
