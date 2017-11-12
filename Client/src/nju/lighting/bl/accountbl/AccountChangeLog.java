package nju.lighting.bl.accountbl;

import nju.lighting.po.account.AccountLogPO;
import nju.lighting.vo.account.AccountLogVO;
import shared.AccountChangeType;

import java.util.Date;

/**
 * Created on 2017/11/12.
 * Description:
 * @author Liao
 */
public class AccountChangeLog {
    private Date time;
    private double delta;
    private double amount;
    private AccountChangeType type;

    public AccountChangeLog(Date time, double delta, double amount, AccountChangeType type) {
        this.time = time;
        this.delta = delta;
        this.amount = amount;
        this.type = type;
    }

    public AccountChangeLog(AccountLogVO vo) {
        this(vo.getTime(), vo.getDelta(), vo.getAmount(), vo.getType());
    }

    public AccountChangeLog(AccountLogPO po) {
        this(po.getTime(), po.getDelta(), po.getAmount(), po.getType());
    }
}
