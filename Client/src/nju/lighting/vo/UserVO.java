package nju.lighting.vo;

import shared.Identity;

/**
 * Created on 2017/10/21.
 * Description:
 * @author Liao
 */
public class UserVO {
    private String username;
    private String jobNumber;
    private Identity identity;
    private boolean authority;

    public UserVO(String username, String jobNumber, Identity identity, boolean authority) {
        this.username = username;
        this.jobNumber = jobNumber;
        this.identity = identity;
        this.authority = authority;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
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
}
