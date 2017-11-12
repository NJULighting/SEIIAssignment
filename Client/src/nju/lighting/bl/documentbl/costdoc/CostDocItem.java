package nju.lighting.bl.documentbl.costdoc;

import nju.lighting.vo.doc.costdoc.CostDocItemVO;
import shared.CostDocItemType;

/**
 * Created on 2017/11/12.
 * Description:
 * @author Liao
 */
public class CostDocItem {
    private CostDocItemType type;
    private double amount;
    private String comment;

    public CostDocItem(CostDocItemVO vo) {
        this(vo.getType(), vo.getAmount(), vo.getComment());
    }

    public CostDocItem(CostDocItemType type, double amount, String comment) {

        this.type = type;
        this.amount = amount;
        this.comment = comment;
    }
}
