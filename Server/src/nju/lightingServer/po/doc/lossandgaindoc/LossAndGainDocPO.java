package nju.lightingServer.po.doc.lossandgaindoc;

import nju.lightingServer.po.doc.DocPO;
import shared.DocType;

import java.util.Date;

public class LossAndGainDocPO extends DocPO {

    private String comment;

    public LossAndGainDocPO(String id, DocType docType, String userId,
                            Date time, String comment) {
        super(id, docType, userId, time);
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
