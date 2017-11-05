package nju.lighting.po;

import java.util.Date;

/**
 * Created on 2017/10/31.
 * Description:
 * @author Liao
 */
public class AccountLogPO {
    private Date time;
    private int delta;
    private int amount;
    private AccountChangeType type;

    public AccountLogPO(Date time, int delta, int amount, AccountChangeType type) {
        this.time = time;
        this.delta = delta;
        this.amount = amount;
        this.type = type;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getDelta() {
        return delta;
    }

    public void setDelta(int delta) {
        this.delta = delta;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public AccountChangeType getType() {
        return type;
    }

    public void setType(AccountChangeType type) {
        this.type = type;
    }
}
