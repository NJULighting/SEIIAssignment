package nju.lighting.bl.accountbl;

import nju.lighting.dataservice.DataFactory;
import nju.lighting.dataservice.accountdataservice.AccountDataService;
import nju.lighting.po.account.AccountPO;
import nju.lighting.vo.account.AccountVO;
import shared.AccountChangeType;
import shared.ResultMessage;

import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Date;

/**
 * Created on 2017/11/12.
 * Description: 账户的领域模型对象
 * @author Liao
 */
class Account {
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

    ResultMessage updateAmount(double total) {
        amount += total;
        logList.addLog(new Date(), total, amount, total < 0 ? AccountChangeType.OUT : AccountChangeType.IN);
        try {
            AccountDataService dataService = DataFactory.getDataBase(AccountDataService.class);
            return dataService.update(toPO());
        } catch (NamingException | RemoteException e) {
            e.printStackTrace();
            return ResultMessage.FAILURE;
        }
    }
}
