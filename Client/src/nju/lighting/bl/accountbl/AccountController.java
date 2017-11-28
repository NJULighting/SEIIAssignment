package nju.lighting.bl.accountbl;

import nju.lighting.blservice.accountblservice.AccountBLService;
import nju.lighting.vo.account.AccountVO;
import shared.ResultMessage;

import java.util.List;

/**
 * Created on 2017/11/6.
 * Description:
 * @author Liao
 */
public class AccountController implements AccountBLService {
    // TODO: 2017/11/28 Replace real logger
    private AccountManager accountManager = AccountManager.getInstance();

    @Override
    public List<AccountVO> getAccountList() {
        return accountManager.getAccountList();
    }

    @Override
    public ResultMessage addAccount(String name, double amount, String id) {
        return accountManager.addAccount(id, name, amount);
    }

    @Override
    public List<AccountVO> findAccounts(String keyword) {
        return null;
        // TODO: 2017/11/28 wait for data level
    }

    @Override
    public AccountVO getAccount(String id) {
        return accountManager.getAccount(id);
    }

    @Override
    public ResultMessage deleteAccount(String id) {
        return accountManager.delete(id);
    }

    @Override
    public ResultMessage modifyAccount(String accountID, String newName) {
        return accountManager.rename(accountID, newName);
    }
}
