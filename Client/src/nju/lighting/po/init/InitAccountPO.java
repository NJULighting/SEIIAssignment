package nju.lighting.po.init;

import nju.lighting.po.account.AccountPO;

/**
 * Created on 2017/11/6.
 * Description:
 * @author Liao
 */
public class InitAccountPO {
    private String id;
    private String name;
    private String initID;
    private double amount;

    public InitAccountPO(String initID, AccountPO accountPO) {
        id = accountPO.getId();
        name = accountPO.getName();
        amount = accountPO.getAmount();
        this.initID = initID;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getInitID() {
        return initID;
    }

    public double getAmount() {
        return amount;
    }
}
