package nju.lighting.po.doc;

import shared.DocState;
import shared.DocType;

import java.util.Date;

public abstract class DocPO {

    private String id;

    private DocType docType;

    private String userId;

    private Date time;

    private String comment;

    private DocState state;

    private String approvalId;

    public DocPO() {

    }

    public DocPO(String id, DocType docType, String userId, Date time) {
        this.id = id;
        this.docType = docType;
        this.userId = userId;
        this.time = time;
    }

    public DocPO(String id, DocType docType, String userId, Date time, String comment, DocState state, String approvalId) {
        this.id = id;
        this.docType = docType;
        this.userId = userId;
        this.time = time;
        this.comment = comment;
        this.state = state;
        this.approvalId = approvalId;
    }

    @Override
    public String toString() {
        return "DocPO{" +
                "id='" + id + '\'' +
                ", docType=" + docType +
                ", userId='" + userId + '\'' +
                ", time=" + time +
                ", comment='" + comment + '\'' +
                ", state=" + state +
                ", approvalId='" + approvalId + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DocType getDocType() {
        return docType;
    }

    public void setDocType(DocType docType) {
        this.docType = docType;
    }

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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public DocState getState() {
        return state;
    }

    public void setState(DocState state) {
        this.state = state;
    }

    public String getApprovalId() {
        return approvalId;
    }

    public void setApprovalId(String approvalId) {
        this.approvalId = approvalId;
    }
}
