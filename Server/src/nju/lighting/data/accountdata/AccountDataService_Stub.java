package nju.lighting.data.accountdata;

import nju.lighting.dataservice.accountdataservice.AccountDataService;
import nju.lighting.po.account.AccountLogPO;
import nju.lighting.po.account.AccountPO;
import shared.ResultMessage;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * Created on 2017/10/22.
 * Description:
 * @author Liao
 */
public class AccountDataService_Stub extends UnicastRemoteObject implements AccountDataService {
    public AccountDataService_Stub() throws RemoteException {}

    @Override
    public ResultMessage insert(AccountPO po) throws RemoteException {
        return po == null ? ResultMessage.FAILURE : ResultMessage.SUCCESS;
    }

    private ArrayList<AccountPO> find(String keyword) throws RemoteException {
        if (keyword.isEmpty()) return null;
        AccountPO accountPO0 = new AccountPO("0000", "Test Account", 10000, null);
        AccountPO accountPO1 = new AccountPO("0001", "Frog Account", 20000, null);
        ArrayList<AccountPO> accounts = new ArrayList<>();
        accounts.add(accountPO0);
        accounts.add(accountPO1);
        return accounts;
    }

    @Override
    public AccountPO get(String id) throws RemoteException {
        if (id.equals("not found"))
            return null;
        return new AccountPO("0001", "Frog Account", 20000, null);
    }

    @Override
    public ArrayList<AccountPO> getAll() throws RemoteException {
        return find("Excited");
    }

    @Override
    public ResultMessage delete(String id) throws RemoteException {
        return id == null ? ResultMessage.FAILURE : ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage update(AccountPO po) throws RemoteException {
        return po == null ? ResultMessage.FAILURE : ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage add(AccountLogPO logPO) {
        return null;
    }
}
