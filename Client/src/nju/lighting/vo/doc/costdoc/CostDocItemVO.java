package nju.lighting.vo.doc.costdoc;

import nju.lighting.po.doc.costdoc.CostDocItemPO;
import shared.CostDocItemType;

/**
 * Created on 2017/11/12.
 * Description:
 * @author Liao
 */
public class CostDocItemVO {
    private int id;
    private CostDocItemType type;
    private double amount;
    private String comment;

    public CostDocItemVO(CostDocItemType type, double amount, String comment) {
        this.type = type;
        this.amount = amount;
        this.comment = comment;
    }

    public CostDocItemVO(int id, CostDocItemType type, double amount, String comment) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.comment = comment;
    }

    public CostDocItemVO toCostDocItem(){
        return new CostDocItemVO(getType(),getAmount(),getComment());
    }

    public CostDocItemType getType() {
        return type;
    }

    public void setType(CostDocItemType type){this.type = type;}

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount){this.amount = amount;}

    public String getComment() {
        return comment;
    }

    public void setComment(String comment){this.comment = comment;}

    public int getId() {
        return id;
    }

    CostDocItemPO toPO() {
        return new CostDocItemPO(type, amount, comment);
    }
}
