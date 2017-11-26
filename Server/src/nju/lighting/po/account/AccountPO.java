package nju.lighting.po.account;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;

/**
 * Created on 2017/10/17.
 * Description:
 * @author Liao
 */

@Entity
@Table(name = "ACCOUNT")
public class AccountPO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private double amount;

    private List<AccountLogPO> changeLogs;

    public AccountPO() {

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
        changeLogs.add(accountLogPO);
    }

    @Transient
    public List<AccountLogPO> getChangeLogs() {
        return changeLogs;
    }
}