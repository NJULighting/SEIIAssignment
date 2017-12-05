package nju.lighting.bl.accountbl;

import nju.lighting.po.account.AccountPO;
import nju.lighting.vo.account.AccountVO;
import shared.AccountChangeType;

import java.util.Calendar;

/**
 * Created on 2017/11/12.
 * Description: 账户的领域模型对象
 * @author Liao
 */
public class Account {
    private final String id;
    private double amount;
    private String name;
    private AccountChangeLogList logList;

    Account(AccountPO po) {
        id = po.getId();
        amount = po.getAmount();
        name = po.getName();
        logList = new AccountChangeLogList(po.getChangeLogs());
    }

    /**
     * Create a new account
     * @param id     id of account
     * @param amount initial amount
     * @param name   account's name
     */
    Account(String id, double amount, String name) {
        this.id = id;
        this.amount = amount;
        this.name = name;
        logList = new AccountChangeLogList();
        if (amount != 0)
            logList.addLog(Calendar.getInstance().getTime(), amount, amount, AccountChangeType.IN);
    }

    /**
     * Convert account model to PO
     * @return relative po
     */
    AccountPO toPO() {
        return new AccountPO(id, name, amount, logList.toPOLogList(id));
    }

    /**
     * Convert account model to VO
     * @return account vo
     */
    AccountVO toVO() {
        return new AccountVO(id, name, amount, logList.toVOLogList());
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
