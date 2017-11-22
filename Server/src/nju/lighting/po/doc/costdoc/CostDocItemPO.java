package nju.lighting.po.doc.costdoc;

import shared.CostDocItemType;

/**
 * Created on 2017/10/21.
 * Description:
 * @author Liao
 */
public class CostDocItemPO {
    private CostDocItemType type;
    private String costDocID;
    private double amount;
    private String comment;

    public CostDocItemPO(CostDocItemType type, String costDocID, double amount, String comment) {
        this.type = type;
        this.costDocID = costDocID;
        this.amount = amount;
        this.comment = comment;
    }

    public String getCostDocID() {
        return costDocID;
    }

    public void setCostDocID(String costDocID) {
        this.costDocID = costDocID;
    }

    public CostDocItemType getType() {
        return type;
    }

    public void setType(CostDocItemType type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}