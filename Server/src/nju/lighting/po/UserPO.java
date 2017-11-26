package nju.lighting.po;


import org.hibernate.annotations.Type;
import shared.Identity;

import javax.persistence.*;

/**
 * Created on 2017/10/17.
 * Description:
 * @author Liao
 */
@Entity
@Table(name = "USER_INFO")
public class UserPO {

    private String name;

    private String password;

    private String id;

    private Identity identity;

    private boolean authorized;

    public UserPO() {

    }

    public UserPO(String name, String password, String id, Identity identity, boolean authorized) {

        this.name = name;
        this.password = password;
        this.id = id;
        this.identity = identity;
        this.authorized = authorized;
    }

    @Override
    public String toString() {
        return "UserPO{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", id='" + id + '\'' +
                ", identity=" + identity +
                ", authorized=" + authorized +
                '}';
    }

    @Column(name = "AUTHORIZED", nullable = false)
    @Type(type = "yes_no")
    public boolean getAuthorized() {
        return authorized;
    }

    public void setAuthorized(boolean authorized) {
        this.authorized = authorized;
    }

    @Column(name = "NAME", nullable = false, length = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "PASSWORD", nullable = false, length = 30)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Id
    @Column(name = "ID", nullable = false, length = 20)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "IDENTITY", length = 10, nullable = false)
    public Identity getIdentity() {
        return identity;
    }

    public void setIdentity(Identity identity) {
        this.identity = identity;
    }
}
