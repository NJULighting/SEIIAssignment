package nju.lighting.po;

/**
 * Created on 2017/10/31.
 * Description:
 * @author Liao
 */
public class AccountLog {
    private long time;
    private int delta;
    private int amount;
    private AccountChangeType type;

    public AccountLog(long time, int delta, int amount, AccountChangeType type) {
        this.time = time;
        this.delta = delta;
        this.amount = amount;
        this.type = type;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
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
