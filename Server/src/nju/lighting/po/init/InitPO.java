package nju.lighting.po.init;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created on 2017/10/17.
 * Description:
 * @author Liao
 */
public class InitPO {

    private int id;

    private Date time;

    private String userID;

    private String url;

    public InitPO(int id, Date time, String userID, String url) {
        this.id = id;
        this.time = time;
        this.userID = userID;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
