package nju.lighting.po.account;

import shared.CSVable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2017/10/17.
 * Description:
 * @author Liao
 */

@Entity
@Table(name = "ACCOUNT")
public class AccountPO implements Serializable, CSVable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private double amount;

    private List<AccountLogPO> changeLogs;

    public AccountPO() {

    }

    @Override
    @Transient
    public String getClassDescription() {
        return "账号,账户名,余额";
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof String && obj.equals(id)) ||
                (obj instanceof AccountPO && ((AccountPO) obj).getId().equals(id));
    }

    @Override
    public String toCSV() {
        return id + "," + name + "," + amount;
    }

    public AccountPO(String id) {
        this.id = id;
    }

    public AccountPO(String id, String name, double amount, List<AccountLogPO> changeLogs) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.changeLogs = changeLogs;
    }

    @Override
    public String toString() {
        return "AccountPO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", changeLogs=" + changeLogs +
                '}';
    }

    @Id
    @Column(name = "ID", nullable = false, length = 20)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "NAME", nullable = false, length = 40)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "AMOUNT", nullable = false)
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setChangeLogs(List<AccountLogPO> changeLogs) {
        this.changeLogs = changeLogs;
    }

    public void addChangeLog(AccountLogPO accountLogPO) {
        if (changeLogs == null)
            changeLogs = new ArrayList<>();
        changeLogs.add(accountLogPO);
    }

    @Transient
    public List<AccountLogPO> getChangeLogs() {
        return changeLogs;
    }
}