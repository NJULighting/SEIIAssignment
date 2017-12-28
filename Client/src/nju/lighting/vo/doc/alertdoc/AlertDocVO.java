package nju.lighting.vo.doc.alertdoc;

import nju.lighting.bl.utils.ListTransformer;
import nju.lighting.po.doc.DocPO;
import nju.lighting.po.doc.alertdoc.AlertDocItemPO;
import nju.lighting.po.doc.alertdoc.AlertDocPO;
import nju.lighting.vo.DocVO;
import shared.DocType;

import java.util.Date;
import java.util.List;

public class AlertDocVO extends DocVO {

    private List<AlertDocItemVO> items;
    private String comment;
    private boolean triggered;
    private boolean expired;

    /**
     * Constructor for pre
     */
    public AlertDocVO(String creatorID, Date time, List<AlertDocItemVO> items, String comment) {
        super(time, DocType.ALERT, creatorID);
        this.items = items;
        this.comment = comment;
        this.triggered = false;
        this.expired = false;
    }

    /**
     * Constructor for bl
     */
    public AlertDocVO(Date time, String creatorId, String docId,
                      List<AlertDocItemVO> items, String comment, boolean triggered, boolean expired) {
        super(time, creatorId, docId, DocType.ALERT);
        this.items = items;
        this.comment = comment;
        this.triggered = triggered;
        this.expired = expired;
    }

    @Override
    public DocPO toPO() {
        List<AlertDocItemPO> itemPOList = ListTransformer.toList(items, AlertDocItemVO::toPO);
        return new AlertDocPO(getType(), getCreatorId(), getTime(), comment, triggered, expired, itemPOList);
    }

    public List<AlertDocItemVO> getItems() {
        return items;
    }

    public String getComment() {
        return comment;
    }

    public boolean isTriggered() {
        return triggered;
    }

    public boolean isExpired() {
        return expired;
    }
}
