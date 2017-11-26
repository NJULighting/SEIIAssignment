package nju.lighting.po.account;

import shared.AccountChangeType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created on 2017/10/31.
 * Description:
 * @author Liao
 */
@Entity
@Table(name = "ACCOUNT_CHANGE")
public class AccountLogPO implements Serializable{

    /**
     * 基本可以忽略，用作主键
     */
    private int id;

    private static final long serialVersionUID = 21314312314L;

    private Date time;

    private double delta;

    private double amount;

    private String accountID;

    private AccountChangeType type;

    /**
     * 必须提供的无参构造器
     */
    public AccountLogPO() {

    }

    public AccountLogPO(Date time, int delta, int amount, AccountChangeType type, String accountID) {
        this.accountID = accountID;
        this.time = time;
        this.delta = delta;
        this.amount = amount;
        this.type = type;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "CHANGE_TIME", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Column(name = "DELTA", nullable = false)
    public double getDelta() {
        return delta;
    }

    public void setDelta(double delta) {
        this.delta = delta;
    }

    @Column(name = "AMOUNT", nullable = false)
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "ACCOUNT_CHANGE_TYPE", nullable = false, length = 20)
    public AccountChangeType getType() {
        return type;
    }

    public void setType(AccountChangeType type) {
        this.type = type;
    }

    @Column(name = "ACCOUNT_ID", nullable = false, length = 20)
    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }
}
