package nju.lighting.bl.accountbl;

import nju.lighting.blservice.accountblservice.AccountBLService;
import nju.lighting.vo.AccountVO;
import nju.lighting.vo.ResultMessage;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created on 2017/10/22.
 * Description:
 * @author Liao
 */
public class AccountBLService_Stub implements AccountBLService {

    @Override
    public ArrayList<AccountVO> getAccountList() throws RemoteException {
        ArrayList<AccountVO> accountVOS = new ArrayList<>();
        accountVOS.add(getAccount("0"));
        accountVOS.add(getAccount("1"));
        return accountVOS;
    }

    @Override
    public ResultMessage addAccount(String name, String amount) throws RemoteException {
        if (name.equals("existed")) return ResultMessage.FAILURE;
        else return ResultMessage.SUCCESS;
    }

    @Override
    public ArrayList<AccountVO> findAccounts(String keyword) throws RemoteException {
        if (keyword.isEmpty()) return null;
        else return getAccountList();
    }

    @Override
    public AccountVO getAccount(String id) throws RemoteException {
        AccountVO accountVO;
        if (id.equals("0"))
            accountVO = new AccountVO("Test Account 0", 100000);
        else
            accountVO = new AccountVO("Test Account 1", 1000000);
        return accountVO;
    }

    @Override
    public ResultMessage deleteAccount(String id) throws RemoteException {
        if (id.equals("0") || id.equals("1"))
            return ResultMessage.SUCCESS;
        return ResultMessage.FAILURE;
    }

    @Override
    public ResultMessage modifyAccount(String oldName, String newName) throws RemoteException {
        if (!oldName.equals(newName))
            return ResultMessage.SUCCESS;
        return ResultMessage.FAILURE;
    }
}
