package nju.lighting.bl.userbl;

import nju.lighting.dataservice.DataFactory;
import nju.lighting.dataservice.userdataservice.UserDataService;
import nju.lighting.po.user.UserPO;
import nju.lighting.vo.UserVO;
import shared.Identity;
import shared.UserChangeInfo;

import javax.naming.NamingException;
import java.rmi.RemoteException;

/**
 * Created on 2017/11/29.
 * Description: Model of User
 * @author Liao
 */
public class User {
    private final String id;
    private String name;
    private String password;
    private Identity identity;
    private boolean authorized;

    User(UserPO po) {
        name = po.getName();
        password = po.getPassword();
        id = po.getId();
        identity = po.getIdentity();
        authorized = po.getAuthorized();
    }

    User(String name, String password, String id, Identity identity, boolean authorized) {
        this.name = name;
        this.password = password;
        this.id = id;
        this.identity = identity;
        this.authorized = authorized;
    }

    UserVO toVO() {
        return new UserVO(name, id, identity, authorized);
    }

    UserPO toPO() {
        return new UserPO(name, password, id, identity, authorized);
    }

    /**
     * Update data from the database
     * @return true if the user is authorized, false otherwise
     */
    boolean isAuthorized() {
        updateFromDataBase();
        return authorized;
    }

    public String getName() {
        updateFromDataBase();
        return name;
    }

    public String getId() {
        return id;
    }

    boolean passwordRight(String password) {
        updateFromDataBase();
        return this.password.equals(password);
    }

    void changePassword(String password) throws RemoteException {
        this.password = password;
        writeToDatabase();
    }

    void rename(String name) throws RemoteException {
        this.name = name;
        writeToDatabase();
    }

    void changeInfo(UserChangeInfo changeInfo) throws RemoteException {
        name = changeInfo.name;
        password = changeInfo.password;
        identity = changeInfo.identity;
        authorized = changeInfo.authorized;
        writeToDatabase();
    }

    /**
     * Update the user's fields
     */
    private void updateFromDataBase() {
        // TODO: 2017/11/30 Synchronization problem
        try {
            UserDataService userDataService = DataFactory.getDataBase(UserDataService.class);
            UserPO newData = userDataService.get(id);
            name = newData.getName();
            authorized = newData.getAuthorized();
            identity = newData.getIdentity();
            password = newData.getPassword();
        } catch (NamingException | RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Write current state to database
     */
    private void writeToDatabase() throws RemoteException {
        try {
            UserDataService userDataService = DataFactory.getDataBase(UserDataService.class);
            userDataService.update(toPO());
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}
