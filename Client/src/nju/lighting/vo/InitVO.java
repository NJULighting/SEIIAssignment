package nju.lighting.vo;

import java.util.Date;

/**
 * Created on 2017/10/21.
 * Description:
 * @author Liao
 */
public class InitVO {
    private final int id;
    private final Date time;
    private final String userID;
    private String url;
    private String username;

    public InitVO(int id, Date time, String userID, String url, String username) {
        this.id = id;
        this.time = time;
        this.userID = userID;
        this.url = url;
        this.username = username;
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

    public String getUsername() {
        return username;
    }
}
