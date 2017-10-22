package nju.lighting.po;

/**
 * Created on 2017/10/21.
 * Description:
 * @author Liao
 */
public class CostDocItem {
    private CostDocItemType type;
    private int amount;
    private String comment;

    public CostDocItem(CostDocItemType type, int amount, String comment) {
        this.type = type;
        this.amount = amount;
        this.comment = comment;
    }

    public CostDocItemType getType() {
        return type;
    }

    public void setType(CostDocItemType type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}