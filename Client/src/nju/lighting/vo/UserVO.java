package nju.lighting.vo;

import shared.Identity;

/**
 * Created on 2017/10/21.
 * Description:
 *
 * @author Liao
 */
public class UserVO {
    private String username;
    private String id;
    private Identity identity;
    private boolean authority;

    public UserVO(String username, String jobNumber, Identity identity, boolean authority) {
        this.username = username;
        this.id = jobNumber;
        this.identity = identity;
        this.authority = authority;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getID() {
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

    public boolean isAuthority() {
        return authority;
    }

    public void setAuthority(boolean authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        if (identity != null)
            return username + "(" + identity.toString() + ")";
        else return username;
    }
}
