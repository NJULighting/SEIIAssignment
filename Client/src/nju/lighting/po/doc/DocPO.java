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
    private String approvalId; // Approver's idd


    /**
     * Constructor for committing. No need to pass id, the id of the
     * committed document will be returned from the data layer
     * @param type document's type
     * @param userId user's id
     * @param createTime the time that the document was created
     */
    public DocPO(DocType type, String userId, Date createTime) {
        this.docType = type;
        this.userId = userId;
        this.createTime = createTime;
    }

    /**
     * Constructor for approval module, which should be used when a history doc
     * which was approved want to transform to a doc po.
     * @param id id of the doc
     * @param docType type of the doc
     * @param userId user's id
     * @param createTime time when it was created
     * @param checkTime time when it was checked
     * @param approvalComment approver's comment
     * @param state result state for this approving
     * @param approvalId approver's id
     */
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
