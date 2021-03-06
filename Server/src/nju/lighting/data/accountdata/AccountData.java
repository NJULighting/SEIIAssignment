package nju.lighting.data.accountdata;

import nju.lighting.data.utils.CommonOperation;
import nju.lighting.dataservice.accountdataservice.AccountDataService;
import nju.lighting.po.account.AccountLogPO;
import nju.lighting.po.account.AccountPO;
import shared.ResultMessage;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;

/**
 * Created on 2017/11/26.
 * Description: 账户模块数据层实现
 * @author iznauy
 */
public class AccountData extends UnicastRemoteObject implements AccountDataService {

    private static final long serialVersionUID = -3411519234284580798L;

    private CommonOperation<AccountPO> accountPOCommonOperation;

    private CommonOperation<AccountLogPO> accountLogPOCommonOperation;

    public AccountData() throws RemoteException {
        accountPOCommonOperation = new CommonOperation<>(AccountPO.class.getName());
        accountLogPOCommonOperation = new CommonOperation<>(AccountLogPO.class.getName());
    }

    @Override
    public ResultMessage insert(AccountPO po) throws RemoteException {
        ResultMessage resultMessage1 =  accountPOCommonOperation.add(po);
        List<AccountLogPO> logPOS = po.getChangeLogs();
        ResultMessage resultMessage2 = accountLogPOCommonOperation.addList(logPOS);
        if (resultMessage1 == ResultMessage.SUCCESS && resultMessage2 == ResultMessage.SUCCESS)
            return ResultMessage.SUCCESS;
        else {
            return ResultMessage.FAILURE;
        }
    }

    @Override
    public AccountPO get(String id) throws RemoteException {
        AccountPO accountPO = accountPOCommonOperation.getBySingleField("id", id);
        if (accountPO == null)
            return null;
        List<AccountLogPO> logPOS = accountLogPOCommonOperation.getListBySingleField("accountID", id);
        accountPO.setChangeLogs(logPOS);
        return accountPO;
    }

    @Override
    public List<AccountPO> getAll() throws RemoteException {
        List<AccountPO> result = accountPOCommonOperation.getAll();
        if (result == null)
            return null;
        List<AccountLogPO> logPOS = accountLogPOCommonOperation.getAll();
        if (logPOS == null) {
            return result;
        }
        HashMap<String, AccountPO> hashMap = new HashMap<>();
        for (AccountPO accountPO: result)
            hashMap.put(accountPO.getId(), accountPO);
        for (AccountLogPO logPO: logPOS) {
            AccountPO po = hashMap.get(logPO.getAccountID());
            po.addChangeLog(logPO);
        }
        return result;
    }

    @Override
    public ResultMessage delete(String id) throws RemoteException {
        ResultMessage resultMessage1 = accountPOCommonOperation.deleteBySingleField("id", id);
        if (resultMessage1 == ResultMessage.FAILURE)
            return ResultMessage.FAILURE;
        return accountLogPOCommonOperation.deleteBySingleField("accountID", id);
    }

    @Override
    public ResultMessage update(AccountPO po) throws RemoteException {
        return accountPOCommonOperation.update(po);
    }


    @Override
    public ResultMessage add(AccountLogPO logPO) {
        return accountLogPOCommonOperation.add(logPO);
    }

    @Override
    public List<AccountPO> fuzzySearchById(String key) throws RemoteException {
        return accountPOCommonOperation.fuzzySearch("id", key);
    }

    @Override
    public List<AccountPO> fuzzySearchByName(String key) throws RemoteException {
        return accountPOCommonOperation.fuzzySearch("name", key);
    }
}
