package nju.lighting.vo;

/**
 * Created on 2017/10/21.
 * Description:
 * @author Liao
 */
public class LogVO {
    private long time;
    private String content;

    public LogVO(long time, String content) {
        this.time = time;
        this.content = content;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
