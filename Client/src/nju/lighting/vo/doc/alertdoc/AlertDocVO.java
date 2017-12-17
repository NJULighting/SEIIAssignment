package nju.lighting.vo.doc.alertdoc;

import nju.lighting.bl.utils.VPOTransformer;
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
    private boolean eligible;
    private boolean expired;

    /**
     * Constructor for pre
     */
    public AlertDocVO(String creatorID, Date time, DocType type, List<AlertDocItemVO> items, String comment,
                      boolean eligible, boolean expired) {
        super(time, type, creatorID);
        this.items = items;
        this.comment = comment;
        this.eligible = eligible;
        this.expired = expired;
    }

    /**
     * Constructor for bl
     */
    public AlertDocVO(Date time, String creatorId, String docId, DocType type,
                      List<AlertDocItemVO> items, String comment, boolean eligible, boolean expired) {
        super(time, creatorId, docId, type);
        this.items = items;
        this.comment = comment;
        this.eligible = eligible;
        this.expired = expired;
    }

    @Override
    public DocPO toPO() {
        List<AlertDocItemPO> itemPOList = VPOTransformer.toVPOList(items, AlertDocItemVO::toPO);
        return new AlertDocPO(getType(), getCreatorId(), getTime(), comment, eligible, expired, itemPOList);
    }

    public List<AlertDocItemVO> getItems() {
        return items;
    }

    public String getComment() {
        return comment;
    }

    public boolean isEligible() {
        return eligible;
    }

    public boolean isExpired() {
        return expired;
    }
}
