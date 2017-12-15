package nju.lighting.po.doc.costdoc;

import shared.CostDocItemType;

import java.io.Serializable;

/**
 * Created on 2017/10/21.
 * Description:
 * @author Liao
 */
public class CostDocItemPO implements Serializable {
    private static final long serialVersionUID = 7563989692066507077L;

    private int id;
    private CostDocItemType type;
    private String docId;
    private double amount;
    private String comment;

    /**
     * Constructor for committing a new document.
     */
    public CostDocItemPO(CostDocItemType type, double amount, String comment) {
        this.type = type;
        this.amount = amount;
        this.comment = comment;
    }

    /**
     * Constructor for approval module.
     */
    public CostDocItemPO(int id, CostDocItemType type, String docId, double amount, String comment) {
        this.id = id;
        this.type = type;
        this.docId = docId;
        this.amount = amount;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public CostDocItemType getType() {
        return type;
    }

    public String getDocId() {
        return docId;
    }

    public double getAmount() {
        return amount;
    }

    public String getComment() {
        return comment;
    }
}