package nju.lighting.po;

import java.util.ArrayList;

/**
 * Created on 2017/10/17.
 * Description:
 * @author Liao
 */
public class AccountPO {
    private final String id;
    private String name;
    private long amount;
    private ArrayList<String> changeLog;

    public AccountPO(String id, String name, long amount, ArrayList<String> changeLog, int key) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.changeLog = changeLog;
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

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public ArrayList<String> getChangeLog() {
        return changeLog;
    }

    public void setChangeLog(ArrayList<String> changeLog) {
        this.changeLog = changeLog;
    }
}