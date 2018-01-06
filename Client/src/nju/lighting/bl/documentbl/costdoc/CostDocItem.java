package nju.lighting.bl.documentbl.costdoc;

import nju.lighting.bl.documentbl.DocItem;
import nju.lighting.bl.documentbl.RedFlush;
import nju.lighting.po.doc.costdoc.CostDocItemPO;
import nju.lighting.vo.doc.costdoc.CostDocItemVO;
import shared.CostDocItemType;
import shared.ResultMessage;

/**
 * Created on 2017/11/12.
 * Description:
 * @author Liao
 */
public class CostDocItem implements DocItem {
    private int id;
    private CostDocItemType type;
    private double amount;
    private String comment;

    CostDocItem(CostDocItemVO vo) {
        this(vo.getType(), vo.getAmount(), vo.getComment());
        id = vo.getId();
    }

    private CostDocItem(CostDocItemType type, double amount, String comment) {

        this.type = type;
        this.amount = amount;
        this.comment = comment;
    }

    CostDocItem(CostDocItemPO po) {
        id = po.getId();
        type = po.getType();
        amount = po.getAmount();
        comment = po.getComment();
    }

    CostDocItemPO toPO(String docId) {
        return new CostDocItemPO(id, type, docId, amount, comment);
    }

    CostDocItemVO toVO() {
        return new CostDocItemVO(id, type, amount, comment);
    }

    @Override
    public void redFlush() {
        id = 0;
        amount = -amount;
        comment = RedFlush.RED_FLUSH_COMMENT;
    }

    @Override
    public ResultMessage approve() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }
}
