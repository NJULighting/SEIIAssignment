package nju.lighting.vo;

/**
 * Created on 2017/10/21.
 * Description:
 * @author Liao
 */
public class AccountVO {
    private String name;
    private int amount;

    public AccountVO(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
