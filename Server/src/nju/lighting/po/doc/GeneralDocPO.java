package nju.lighting.po.doc;

import java.util.Date;

public abstract class GeneralDocPO extends DocPO {

    private String userId;

    private String time;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public GeneralDocPO(String id, DocType docType, String userId, String time) {
        super(id, docType);
        this.userId = userId;
        this.time = time;
    }
}
