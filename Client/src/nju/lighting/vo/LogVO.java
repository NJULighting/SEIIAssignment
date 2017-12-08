package nju.lighting.vo;

import shared.Identity;

import java.util.Date;

/**
 * Created on 2017/10/21.
 * Description:
 * @author Liao
 */
public class LogVO {
    private Date time;
    private String behavior;
    private String creator;
    private Identity identity;

    public LogVO(Date time, String behavior, String creator, Identity identity) {
        this.time = time;
        this.behavior = behavior;
        this.creator = creator;
        this.identity = identity;
    }

    public String getCreator() {
        return creator;
    }

    public Identity getIdentity() {
        return identity;
    }

    public Date getTime() {
        return time;
    }


    public String getBehavior() {
        return behavior;
    }

    public void setBehavior(String behavior) {
        this.behavior = behavior;
    }
}
