package nju.lighting.vo.doc.lossandgaindoc;

import nju.lighting.bl.utils.ListTransformer;
import nju.lighting.po.doc.DocPO;
import nju.lighting.po.doc.lossandgaindoc.LossAndGainDocPO;
import nju.lighting.po.doc.lossandgaindoc.LossAndGainItemPO;
import nju.lighting.vo.DocVO;
import shared.DocType;

import java.util.Date;
import java.util.List;

public class LossAndGainDocVO extends DocVO {

    private List<LossAndGainDocItemVO> items;
    private String comment;

    /**
     * Constructor for bl
     */
    public LossAndGainDocVO(Date time, String creatorId, String docId, DocType type,
                            List<LossAndGainDocItemVO> items, String comment) {
        super(time, creatorId, docId, type);
        this.items = items;
        this.comment = comment;
    }

    /**
     * Constructor for pre
     */
    public LossAndGainDocVO(Date time, String creatorId, List<LossAndGainDocItemVO> items, String comment) {
        super(time, DocType.LOSS_AND_GAIN, creatorId);
        this.items = items;
        this.comment = comment;
    }

    public List<LossAndGainDocItemVO> getItems() {
        return items;
    }

    public String getComment() {
        return comment;
    }

    @Override
    public DocPO toPO() {
        List<LossAndGainItemPO> itemPOList = ListTransformer.toList(items, LossAndGainDocItemVO::toPO);
        return new LossAndGainDocPO(getType(), getCreatorId(), getTime(), comment, itemPOList);
    }
}
