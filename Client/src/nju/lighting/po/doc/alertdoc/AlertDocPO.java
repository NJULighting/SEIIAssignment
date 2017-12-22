package nju.lighting.po.doc.alertdoc;

import nju.lighting.po.doc.DocPO;
import shared.DocState;
import shared.DocType;

import java.util.Date;
import java.util.List;

public class AlertDocPO extends DocPO {

    private static final long serialVersionUID = 43096723613323999L;
    private String comment;
    private boolean triggered;
    private boolean expired;
    private List<AlertDocItemPO> itemPOS;

    /**
     * Constructor for committing a new document.
     */
    public AlertDocPO(DocType type, String userId, Date createTime, String comment,
                      boolean triggered, boolean expired, List<AlertDocItemPO> itemPOS) {
        super(type, userId, createTime);
        this.comment = comment;
        this.triggered = triggered;
        this.expired = expired;
        this.itemPOS = itemPOS;
    }

    /**
     * Constructor for approval module.
     */
    public AlertDocPO(String id, DocType docType, String userId, Date createTime,
                      Date checkTime, String approvalComment, DocState state, String approvalId,
                      String comment, boolean triggered, boolean expired, List<AlertDocItemPO> itemPOS) {
        super(id, docType, userId, createTime, checkTime, approvalComment, state, approvalId);
        this.comment = comment;
        this.triggered = triggered;
        this.expired = expired;
        this.itemPOS = itemPOS;
    }

    public String getComment() {
        return comment;
    }

    public boolean isTriggered() {
        return triggered;
    }

    public boolean isExpired() {
        return expired;
    }

    public List<AlertDocItemPO> getItemPOS() {
        return itemPOS;
    }

}



