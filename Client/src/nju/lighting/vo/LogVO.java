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
    private String creatorId;
    private Identity identity;

    public LogVO(Date time, String behavior, String creatorId, Identity identity) {
        this.time = time;
        this.behavior = behavior;
        this.creatorId = creatorId;
        this.identity = identity;
    }

    public String getCreatorId() {
        return creatorId;
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
}
