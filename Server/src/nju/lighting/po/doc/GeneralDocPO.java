package nju.lighting.po.doc;

import java.util.Date;

public abstract class GeneralDocPO extends DocPO {

    private String userId;

    private Date time;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public GeneralDocPO(String id, DocType docType, String userId, Date time) {
        super(id, docType);
        this.userId = userId;
        this.time = time;
    }
}
