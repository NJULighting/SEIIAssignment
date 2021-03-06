package nju.lighting.po.doc;

import shared.DocState;
import shared.DocType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@MappedSuperclass
public abstract class DocPO implements Serializable{

    private static final long serialVersionUID = 8223454946318384837L;
    private String id;

    private DocType docType;

    private String userId;

    private Date createTime;

    private Date checkTime;

    private String approvalComment;

    private DocState state = DocState.UN_CHECKED;

    private String approvalId;

    public DocPO(DocType docType, String userId, Date createTime) {
        this.docType = docType;
        this.userId = userId;
        this.createTime = createTime;
    }

    public DocPO() {

    }

    @Transient
    public abstract List<Object> getItems();

    public abstract void setItems(List<Object> list);

    public DocPO(String id, DocType docType, String userId, Date createTime) {
        this.id = id;
        this.docType = docType;
        this.userId = userId;
        this.createTime = createTime;
    }

    public DocPO(String id, DocType docType, String userId, Date createTime,
                 Date checkTime, String approvalComment, DocState state, String approvalId) {
        this.id = id;
        this.docType = docType;
        this.userId = userId;
        this.createTime = createTime;
        this.checkTime = checkTime;
        this.approvalComment = approvalComment;
        this.state = state;
        this.approvalId = approvalId;
    }

    @Id
    @Column(name = "ID", nullable = false, length = 20)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Transient
    public DocType getDocType() {
        return docType;
    }

    public void setDocType(DocType docType) {
        this.docType = docType;
    }

    @Column(nullable = false, name = "USER_ID", length = 20)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_TIME", nullable = false)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "CHECK_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    @Column(name = "CHECKER_COMMENT", length = 300)
    public String getApprovalComment() {
        return approvalComment;
    }

    public void setApprovalComment(String approvalComment) {
        this.approvalComment = approvalComment;
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "STATE", length = 20)
    public DocState getState() {
        return state;
    }

    public void setState(DocState state) {
        this.state = state;
    }

    @Column(name = "CHECKER_ID", length = 20)
    public String getApprovalId() {
        return approvalId;
    }

    public void setApprovalId(String approvalId) {
        this.approvalId = approvalId;
    }
}
