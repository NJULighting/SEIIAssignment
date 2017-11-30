package nju.lighting.bl.userbl;

import nju.lighting.dataservice.DataFactory;
import nju.lighting.dataservice.userdataservice.UserDataService;
import nju.lighting.po.user.UserPO;
import nju.lighting.vo.UserVO;
import shared.Identity;

import javax.naming.NamingException;
import java.rmi.RemoteException;

/**
 * Created on 2017/11/29.
 * Description: Model of User
 * @author Liao
 */
public class User {
    private String name;
    private String password;
    private String id;
    private Identity identity;
    private boolean authorized;

    public User(UserPO po) {
        name = po.getName();
        password = po.getPassword();
        id = po.getId();
        identity = po.getIdentity();
        authorized = po.getAuthorized();
    }

    public User(String name, String password, String id, Identity identity, boolean authorized) {
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
     * @return
     */
    boolean isAuthorized() {
        updateState();
        return authorized;
    }

    public String getId() {
        updateState();
        return id;
    }

    private void updateState() {
        // TODO: 2017/11/30 Synchronization problem
        try {
            UserDataService userDataService = DataFactory.getDataBase(UserDataService.class);
            UserPO newData = userDataService.get(id);
            id = newData.getId();
            name = newData.getName();
            authorized = newData.getAuthorized();
            identity = newData.getIdentity();
            password = newData.getPassword();
        } catch (NamingException | RemoteException e) {
            e.printStackTrace();
        }
    }
}
