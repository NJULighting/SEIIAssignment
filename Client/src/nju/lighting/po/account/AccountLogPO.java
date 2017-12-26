package nju.lighting.po.account;

import shared.AccountChangeType;

import java.io.Serializable;
import java.util.Date;

/**
 * Created on 2017/10/31.
 * Description:
 * @author Liao
 */
public class AccountLogPO implements Serializable{
    private static final long serialVersionUID = 21314312314L;
    private int id;
    private Date time;
    private double delta;
    private double amount;
    private String accountID;
    private AccountChangeType type;

    public AccountLogPO(Date time, double delta, double amount, AccountChangeType type, String accountID) {
        this.accountID = accountID;
        this.time = time;
        this.delta = delta;
        this.amount = amount;
        this.type = type;
    }

    public AccountLogPO(int id, Date time, double delta, double amount, String accountID, AccountChangeType type) {
        this.id = id;
        this.time = time;
        this.delta = delta;
        this.amount = amount;
        this.accountID = accountID;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public double getDelta() {
        return delta;
    }

    public void setDelta(double delta) {
        this.delta = delta;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public AccountChangeType getType() {
        return type;
    }

    public void setType(AccountChangeType type) {
        this.type = type;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }
}
