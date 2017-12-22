package nju.lighting.bl.documentbl.costdoc;

import nju.lighting.po.doc.costdoc.CostDocItemPO;
import nju.lighting.vo.doc.costdoc.CostDocItemVO;
import shared.CostDocItemType;

/**
 * Created on 2017/11/12.
 * Description:
 * @author Liao
 */
public class CostDocItem {
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
        type = po.getType();
        amount = po.getAmount();
        comment = po.getComment();
    }

    CostDocItemPO toPO(String docId) {
        return new CostDocItemPO(id, type, docId, amount, comment);
    }
}
