package nju.lighting.vo.account;

import java.util.ArrayList;

/**
 * Created on 2017/10/21.
 * Description:
 * @author Liao
 */
public class AccountVO {
    private String name;
    private double amount;
    private ArrayList<AccountLogVO> accountLogs;

    public AccountVO(String name, double amount, ArrayList<AccountLogVO> accountLogs) {
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

    public double getAmount() {
        return amount;
    }
}
