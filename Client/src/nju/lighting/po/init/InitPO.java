package nju.lighting.po.init;

import java.io.Serializable;
import java.util.Date;

/**
 * Created on 2017/10/17.
 * Description:
 * @author Liao
 */
public class InitPO implements Serializable {
    private static final long serialVersionUID = 2418894342385L;
    private int id;
    private Date time;
    private String userID;
    private String url;

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

    public int getId() {
        return id;
    }

    public Date getTime() {
        return time;
    }

    public String getUserID() {
        return userID;
    }

    public String getUrl() {
        return url;
    }
}
