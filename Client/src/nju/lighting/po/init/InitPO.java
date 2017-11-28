package nju.lighting.po.init;

import javax.persistence.*;
import java.util.Date;

/**
 * Created on 2017/10/17.
 * Description:
 * @author Liao
 */
@Entity
@Table(name = "INIT_INFO")
public class InitPO {

    private int id;

    private Date time;

    private String userID;

    private String url;

    public InitPO() {}

    public InitPO(Date time, String userID, String url) {
        this.time = time;
        this.userID = userID;
        this.url = url;
    }

    public InitPO(int id, Date time, String userID, String url) {
        this.id = id;
        this.time = time;
        this.userID = userID;
        this.url = url;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_TIME", nullable = false)
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Column(name = "USER_ID", length = 20, nullable = false)
    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    @Column(name = "URL", length = 255)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
