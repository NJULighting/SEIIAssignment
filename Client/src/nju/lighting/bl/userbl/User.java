package nju.lighting.bl.userbl;

import nju.lighting.po.user.UserPO;
import nju.lighting.vo.UserVO;
import shared.Identity;

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

    boolean passwordRight(String password) {
        return this.password.equals(password);
    }

    UserVO toVO() {
        return new UserVO(name, id, identity, authorized);
    }

    UserPO toPO() {
        return new UserPO(name, password, id, identity, authorized);
    }
}
