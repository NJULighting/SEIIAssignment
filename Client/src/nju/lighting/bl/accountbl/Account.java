package nju.lighting.bl.accountbl;

import nju.lighting.po.account.AccountLogPO;
import nju.lighting.po.account.AccountPO;

import java.util.ArrayList;

/**
 * Created on 2017/11/12.
 * Description: 账户的领域模型对象
 * @author Liao
 */
public class Account {
    private String id;
    private double amount;
    private String name;
    private AccountChangeLogList logList;

    public Account(AccountPO po) {
        this(po.getId(), po.getAmount(), po.getName());
        convertLogList(po.getChangeLogs());
    }

    private void convertLogList(ArrayList<AccountLogPO> changeLogs) {
        for (AccountLogPO log : changeLogs) {

        }
    }

    public Account(String id, double amount, String name) {
        this.id = id;
        this.amount = amount;
        this.name = name;

    }

    /**
     * Convert account model to PO
     * @return relative po
     */
    public AccountPO toPO() {
        return new AccountPO(id, name, amount, logList.toPOLogList());
    }

    public String getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }

    public AccountChangeLogList getLogList() {
        return logList;
    }

    /**
     * 更新账户金额，同时更新账户日志
     */
    public void updateAmount() {

    }
}
