package nju.lighting.po.doc.lossandgaindoc;

import nju.lighting.po.doc.DocPO;
import shared.DocState;
import shared.DocType;

import java.util.Date;
import java.util.List;

public class LossAndGainDocPO extends DocPO {

    private static final long serialVersionUID = 6918970037634014229L;

    private String comment;
    private List<LossAndGainItemPO> itemPOS;

    /**
     * Constructor for committing a new document.
     */
    public LossAndGainDocPO(DocType type, String userId, Date createTime,
                            String comment, List<LossAndGainItemPO> itemPOS) {
        super(type, userId, createTime);
        this.comment = comment;
        this.itemPOS = itemPOS;
    }

    /**
     * Constructor for approval module.
     */
    public LossAndGainDocPO(String id, DocType docType, String userId, Date createTime, Date checkTime, String approvalComment,
                            DocState state, String approvalId, String comment, List<LossAndGainItemPO> itemPOS) {
        super(id, docType, userId, createTime, checkTime, approvalComment, state, approvalId);
        this.comment = comment;
        this.itemPOS = itemPOS;
    }

    public String getComment() {
        return comment;
    }

    public List<LossAndGainItemPO> getItemPOS() {
        return itemPOS;
    }
}
