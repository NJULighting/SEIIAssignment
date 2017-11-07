package nju.lighting.vo.account;

import shared.AccountChangeType;

import java.util.Date;

/**
 * Created on 2017/11/7.
 * Description:
 * @author Liao
 */
public class AccountLogVO {

    private Date time;
    private double delta;
    private double amount;
    private AccountChangeType type;

    public AccountLogVO(Date time, double delta, double amount, AccountChangeType type) {
        this.time = time;
        this.delta = delta;
        this.amount = amount;
        this.type = type;
    }

    public Date getTime() {
        return time;
    }

    public double getDelta() {
        return delta;
    }

    public double getAmount() {
        return amount;
    }

    public AccountChangeType getType() {
        return type;
    }
}
