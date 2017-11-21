package nju.lightingServer.po;


import shared.Identity;

/**
 * Created on 2017/10/17.
 * Description:
 * @author Liao
 */
public class UserPO {
    private String name;
    private String password;
    private String id;
    private Identity identity;
    private boolean authorized;

    public UserPO(String name, String password, String id, Identity identity, boolean authorized) {

        this.name = name;
        this.password = password;
        this.id = id;
        this.identity = identity;
        this.authorized = authorized;
    }

    public boolean getAuthorized() {
        return authorized;
    }

    public void setAuthorized(boolean authorized) {
        this.authorized = authorized;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Identity getIdentity() {
        return identity;
    }

    public void setIdentity(Identity identity) {
        this.identity = identity;
    }
}
