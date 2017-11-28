package nju.lighting.po.account;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created on 2017/10/17.
 * Description:
 * @author Liao
 */
public class AccountPO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private double amount;
    private ArrayList<AccountLogPO> changeLogs;

    public AccountPO(String id, String name, double amount, ArrayList<AccountLogPO> changeLogs) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.changeLogs = changeLogs;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public ArrayList<AccountLogPO> getChangeLogs() {
        return changeLogs;
    }

    public void setChangeLogs(ArrayList<AccountLogPO> changeLogs) {
        this.changeLogs = changeLogs;
    }
}