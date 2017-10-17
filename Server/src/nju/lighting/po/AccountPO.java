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
    private long money;
    private ArrayList<String> changeLog;

    public AccountPO(String id, String name, long money, ArrayList<String> changeLog, int key) {
        this.id = id;
        this.name = name;
        this.money = money;
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

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public ArrayList<String> getChangeLog() {
        return changeLog;
    }

    public void setChangeLog(ArrayList<String> changeLog) {
        this.changeLog = changeLog;
    }
}
