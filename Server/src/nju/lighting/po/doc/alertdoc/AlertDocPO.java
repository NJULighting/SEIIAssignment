package nju.lighting.po.doc.alertdoc;

import nju.lighting.po.doc.DocPO;
import shared.DocState;
import shared.DocType;

import java.util.Date;
import java.util.List;

public class AlertDocPO extends DocPO {

    private String comment;

    private boolean triggered;

    private boolean expired;

    private List<AlertDocItemPO> itemPOS;

    public AlertDocPO(String id, DocType docType, String userId, Date time, String comment, boolean triggered, boolean expired) {
        super(id, docType, userId, time);
        this.comment = comment;
        this.triggered = triggered;
        this.expired = expired;
    }

    public AlertDocPO(String id, DocType docType, String userId, Date time, String approvalComment,
                      DocState state, String approvalId, String comment, boolean triggered, boolean expired,
                      List<AlertDocItemPO> itemPOS) {
        super(id, docType, userId, time, approvalComment, state, approvalId);
        this.comment = comment;
        this.triggered = triggered;
        this.expired = expired;
        this.itemPOS = itemPOS;
    }

    public AlertDocPO() {
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



