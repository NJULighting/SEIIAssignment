package nju.lighting.vo.account;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2017/10/21.
 * Description:
 * @author Liao
 */
public class AccountVO {
    private String id;
    private String name;
    private double amount;
    private List<AccountLogVO> accountLogs;

    public AccountVO(String id, String name, double amount, ArrayList<AccountLogVO> accountLogs) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.accountLogs = accountLogs;
    }

    public List<AccountLogVO> getAccountLogs() {
        return accountLogs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
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
