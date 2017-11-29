package nju.lighting.po.doc.alertdoc;

import nju.lighting.po.doc.DocPO;
import org.hibernate.annotations.Type;
import shared.DocState;
import shared.DocType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ALERT_DOC")
public class AlertDocPO extends DocPO {

    private String comment;

    private boolean triggered;

    private boolean expired;

    private List<AlertDocItemPO> itemPOS;

    public AlertDocPO(String id, DocType docType, String userId, Date time,
                      String comment, boolean triggered, boolean expired) {
        super(id, docType, userId, time);
        this.comment = comment;
        this.triggered = triggered;
        this.expired = expired;
    }

    public AlertDocPO(String id, DocType docType, String userId, Date createTime,
                      Date checkTime, String approvalComment, DocState state,
                      String approvalId, String comment, boolean triggered,
                      boolean expired, List<AlertDocItemPO> itemPOS) {
        super(id, docType, userId, createTime, checkTime, approvalComment, state, approvalId);
        this.comment = comment;
        this.triggered = triggered;
        this.expired = expired;
        this.itemPOS = itemPOS;
    }

    public AlertDocPO() {
    }

    @Column(name = "COMMENT", length = 300)
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Column(name = "TRIGGERED", nullable = false)
    @Type(type = "yes_no")
    public boolean isTriggered() {
        return triggered;
    }

    public void setTriggered(boolean triggered) {
        this.triggered = triggered;
    }

    @Type(type = "yes_no")
    @Column(name = "EXPIRED", nullable = false)
    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    @Transient
    public List<AlertDocItemPO> getItemPOS() {
        return itemPOS;
    }

    public void setItemPOS(List<AlertDocItemPO> itemPOS) {
        this.itemPOS = itemPOS;
    }

    @Override
    @Transient
    public List<Object> getItems() {
        List<Object> list = new ArrayList<>();
        list.addAll(itemPOS);
        return list;
    }

    @Override
    public void setItems(List<Object> list) {
        itemPOS = new ArrayList<>();
        for (Object o: list) {
            itemPOS.add((AlertDocItemPO)o);
        }
    }
}



