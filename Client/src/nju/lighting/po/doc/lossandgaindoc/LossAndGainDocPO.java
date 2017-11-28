package nju.lighting.po.doc.lossandgaindoc;

import nju.lighting.po.doc.DocPO;
import shared.DocType;

import java.util.Date;
import java.util.List;

public class LossAndGainDocPO extends DocPO {

    private String comment;

    private List<LossAndGainItemPO> itemPOS;

    public LossAndGainDocPO(String id, DocType docType, String userId,
                            Date time, String comment) {
        super(id, docType, userId, time);
        this.comment = comment;
    }

    public LossAndGainDocPO(String id, DocType docType, String userId,
                            Date time, String comment, List<LossAndGainItemPO> itemPOS) {
        super(id, docType, userId, time);
        this.comment = comment;
        this.itemPOS = itemPOS;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<LossAndGainItemPO> getItemPOS() {
        return itemPOS;
    }

    public void setItemPOS(List<LossAndGainItemPO> itemPOS) {
        this.itemPOS = itemPOS;
    }
}
