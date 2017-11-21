package nju.lightingServer.po.doc.alertdoc;

import nju.lightingServer.po.doc.DocPO;
import shared.DocType;

import java.util.Date;

public class AlertDocPO extends DocPO {

    private String comment;

    private boolean triggered;

    private boolean expired;

    public AlertDocPO(String id, DocType docType, String userId, Date time, String comment, boolean triggered, boolean expired) {
        super(id, docType, userId, time);
        this.comment = comment;
        this.triggered = triggered;
        this.expired = expired;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isTriggered() {
        return triggered;
    }

    public void setTriggered(boolean triggered) {
        this.triggered = triggered;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }
}



