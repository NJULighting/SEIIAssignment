package nju.lighting.bl.accountbl;

import nju.lighting.po.account.AccountLogPO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2017/11/12.
 * Description: 账户资金变动日志记录列表，拥有计算收支总和的功能
 * @author Liao
 */
public class AccountChangeLogList {
    private List<AccountChangeLog> logList;

    public AccountChangeLogList() {
        logList = new ArrayList<>();
    }

    public List<AccountChangeLog> getLogList() {
        return logList;
    }

    public ArrayList<AccountLogPO> toPOLogList() {
        return null; // TODO: 2017/11/21 add code here
    }
}
