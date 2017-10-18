package nju.lighting.po;

/**
 * Created on 2017/10/17.
 * Description:
 * @author Liao
 */
public class LogPO {
    private long time;
    private String id;
    private String content;

    public LogPO(long time, String id, String content) {
        this.time = time;
        this.id = id;
        this.content = content;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
