package nju.lighting.po.doc;

import shared.DocState;
import shared.DocType;

import java.io.Serializable;
import java.util.Date;

public abstract class DocPO implements Serializable {
    private static final long serialVersionUID = 8223454946318384837L;
    private String id;
    private DocType docType;
    private String userId;
    private Date createTime;
    private Date checkTime;
    private String approvalComment;
    private DocState state = DocState.UN_CHECKED;
    private String approvalId;


    public DocPO(String id, DocType docType, String userId, Date time) {
        this.id = id;
        this.docType = docType;
        this.userId = userId;
        this.createTime = time;
    }

    public DocPO(String id, DocType docType, String userId, Date createTime, Date checkTime,
                 String approvalComment, DocState state, String approvalId) {
        this.id = id;
        this.docType = docType;
        this.userId = userId;
        this.createTime = createTime;
        this.checkTime = checkTime;
        this.approvalComment = approvalComment;
        this.state = state;
        this.approvalId = approvalId;
    }

    public String getId() {
        return id;
    }

    public DocType getDocType() {
        return docType;
    }

    public String getUserId() {
        return userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public String getApprovalComment() {
        return approvalComment;
    }

    public DocState getState() {
        return state;
    }

    public String getApprovalId() {
        return approvalId;
    }
}
