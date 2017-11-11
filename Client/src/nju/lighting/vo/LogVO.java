package nju.lighting.vo;

import shared.Identity;

/**
 * Created on 2017/10/21.
 * Description:
 * @author Liao
 */
public class LogVO {
    private long time;
    private String behavior;
    private String creator;
    private Identity identity;

    public LogVO(long time, String behavior) {
        this.time = time;
        this.behavior = behavior;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getBehavior() {
        return behavior;
    }

    public void setBehavior(String behavior) {
        this.behavior = behavior;
    }
}
