package nju.lighting.vo.account;

import java.util.ArrayList;

/**
 * Created on 2017/10/21.
 * Description:
 * @author Liao
 */
public class AccountVO {
    private String id;
    private String name;
    private double amount;
    private ArrayList<AccountLogVO> accountLogs;

    public AccountVO(String id, String name, double amount, ArrayList<AccountLogVO> accountLogs) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.accountLogs = accountLogs;
    }

    public ArrayList<AccountLogVO> getAccountLogs() {
        return accountLogs;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccountVO accountVO = (AccountVO) o;

        return id != null ? id.equals(accountVO.id) : accountVO.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
