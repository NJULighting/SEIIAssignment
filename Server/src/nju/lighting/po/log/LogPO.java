package nju.lighting.po.log;

import javax.persistence.*;
import java.util.Date;

/**
 * Created on 2017/10/17.
 * Description:
 * @author Liao
 */
@Entity
@Table(name = "LOG")
public class LogPO {

    private int id;

    private Date time;

    private String userID;

    private String content;

    @Override
    public String toString() {
        return "LogPO{" +
                "id=" + id +
                ", time=" + time +
                ", userID='" + userID + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public LogPO() {

    }

    public LogPO(Date time, String userID, String content) {
        this.time = time;
        this.userID = userID;
        this.content = content;
    }

    public LogPO(Date time, String content, int id, String userID) {
        this.time = time;
        this.id = id;
        this.content = content;
        this.userID = userID;
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

    @Column(nullable = false, name = "CONTENT", length = 300)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "USER_ID", nullable = false, length = 20)
    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
