package nju.lighting.data.accountdata;

import nju.lighting.dataservice.accountdataservice.AccountDataService;
import nju.lighting.po.AccountPO;
import nju.lighting.po.ResultMessage;

import java.util.ArrayList;

/**
 * Created on 2017/10/22.
 * Description:
 * @author Liao
 */
public class AccountDataService_Stub implements AccountDataService {
    @Override
    public ResultMessage insert(AccountPO po) {
        return po == null ? ResultMessage.FAILURE : ResultMessage.SUCCESS;
    }

    @Override
    public ArrayList<AccountPO> find(String keyword) {
        if (keyword.isEmpty()) return null;
        ArrayList<String> changeLog = new ArrayList<>();
        changeLog.add("Excited");
        changeLog.add("Naive");
        AccountPO accountPO0 = new AccountPO("0000", "Test Account", 10000, changeLog, 0);
        AccountPO accountPO1 = new AccountPO("0001", "Frog Account", 20000, changeLog, 1);
        ArrayList<AccountPO> accounts = new ArrayList<>();
        accounts.add(accountPO0);
        accounts.add(accountPO1);
        return accounts;
    }

    @Override
    public AccountPO get(String id) {
        ArrayList<String> changeLog = new ArrayList<>();
        changeLog.add("Excited");
        changeLog.add("Naive");
        return  new AccountPO("0001", "Frog Account", 20000, changeLog, 1);
    }

    @Override
    public ArrayList<AccountPO> getAll() {
        return find("Excited");
    }

    @Override
    public ResultMessage delete(AccountPO po) {
        return po == null ? ResultMessage.FAILURE : ResultMessage.SUCCESS;
    }

    @Override
    public void finish() {
        System.out.println("Finish");
    }

    @Override
    public void init() {
        System.out.println("Init");
    }

    @Override
    public ResultMessage update(AccountPO po) {
        return po == null ? ResultMessage.FAILURE : ResultMessage.SUCCESS;
    }
}
