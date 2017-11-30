package nju.lighting.bl.accountbl;

import nju.lighting.po.account.AccountLogPO;
import nju.lighting.vo.account.AccountLogVO;
import shared.AccountChangeType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

/**
 * Created on 2017/11/12.
 * Description: 账户资金变动日志记录列表，拥有计算收支总和的功能
 * @author Liao
 */
class AccountChangeLogList {
    private List<AccountChangeLog> logList;

    AccountChangeLogList() {
        logList = new ArrayList<>();
    }

    AccountChangeLogList(List<AccountLogPO> changeLogs) {
        // Check null
        if (changeLogs == null)
            logList = new ArrayList<>();
        else
            for (AccountLogPO po : changeLogs) {
                logList.add(new AccountChangeLog(po));
            }
    }

    /**
     * Add a account log
     * @param time   time of log
     * @param delta  delta of amount
     * @param amount amount after changed
     * @param type   type of this change
     */
    void addLog(Date time, double delta, double amount, AccountChangeType type) {
        logList.add(new AccountChangeLog(time, delta, amount, type));
    }

    ArrayList<AccountLogPO> toPOLogList(String accountID) {
        return toList(accountChangeLog -> accountChangeLog.toPO(accountID));
    }

    ArrayList<AccountLogVO> toVOLogList() {
        return toList(AccountChangeLog::toVO);
    }

    /**
     * A help method to help create a VO or PO list
     * @param function convert <code>AccountChangeLog</code> to <code>PO</code> or <code>VO</code>
     * @param <R>      should be PO or VO
     * @return <code>ArrayList</code> with <code>AccountLogPO</code> or <code>AccountLogVO</code>
     */
    private <R> ArrayList<R> toList(Function<AccountChangeLog, R> function) {
        ArrayList<R> target = new ArrayList<>();
        for (AccountChangeLog log : logList) {
            target.add(function.apply(log));
        }
        return target;
    }
}
