package nju.lighting.po.doc;

import shared.DocState;
import shared.DocType;

import java.util.Date;

public abstract class DocPO {

    private String id;

    private DocType docType;

    private String userId;

    private Date time;

    private String approvalComment;

    private DocState state = DocState.UN_CHECKED;

    private String approvalId;

    public DocPO() {

    }

    public DocPO(String id, DocType docType, String userId, Date time, String approvalComment, DocState state, String approvalId) {
        this.id = id;
        this.docType = docType;
        this.userId = userId;
        this.time = time;
        this.approvalComment = approvalComment;
        this.state = state;
        this.approvalId = approvalId;
    }

    public DocPO(String id, DocType docType, String userId, Date time) {
        this.id = id;
        this.docType = docType;
        this.userId = userId;
        this.time = time;
    }

    @Override
    public String toString() {
        return "DocPO{" +
                "id='" + id + '\'' +
                ", docType=" + docType +
                ", userId='" + userId + '\'' +
                ", time=" + time +
                ", approvalComment='" + approvalComment + '\'' +
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

    public String getApprovalComment() {
        return approvalComment;
    }

    public void setApprovalComment(String approvalComment) {
        this.approvalComment = approvalComment;
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
