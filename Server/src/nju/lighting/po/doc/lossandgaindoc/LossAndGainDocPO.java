package nju.lighting.po.doc.lossandgaindoc;

import nju.lighting.po.doc.DocType;
import nju.lighting.po.doc.DocPO;

import java.util.Date;

public class LossAndGainDocPO extends DocPO {

    private String comment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LossAndGainDocPO(String id, DocType docType, String userId,
                            Date time, String comment) {
        super(id, docType, userId, time);
        this.comment = comment;
    }

}
