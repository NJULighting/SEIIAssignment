package nju.lighting.po.log;

import java.io.Serializable;
import java.util.Date;

/**
 * Created on 2017/10/17.
 * Description:
 * @author Liao
 */
public class LogPO implements Serializable{
    private static final long serialVersionUID = 124623587452L;

    private int id;
    private Date time;
    private String userID;
    private String content;

    public LogPO(Date time, String content, int id, String userID) {
        this.time = time;
        this.id = id;
        this.content = content;
        this.userID = userID;
    }

    public int getId() {
        return id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public String getUserID() {
        return userID;
    }
}
