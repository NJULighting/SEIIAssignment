package nju.lighting.po;

/**
 * Created on 2017/10/17.
 * Description:
 * @author Liao
 */
public class UserPO {
    private String username;
    private String password;
    private String jobNumber;
    private Identity identity;
    private boolean authority;

    public UserPO(String username, String password, String jobNumber, Identity identity) {

        this.username = username;
        this.password = password;
        this.jobNumber = jobNumber;
        this.identity = identity;
        this.authority = authority;

    }

    public boolean getAuthority() {
        return authority;
    }

    public void setAuthority(boolean authority) {
        this.authority = authority;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}
