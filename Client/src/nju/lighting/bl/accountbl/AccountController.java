package nju.lighting.bl.accountbl;

import nju.lighting.blservice.accountblservice.AccountBLService;
import nju.lighting.vo.account.AccountVO;
import shared.ResultMessage;

import java.util.ArrayList;

/**
 * Created on 2017/11/6.
 * Description:
 * @author Liao
 */
public class AccountController implements AccountBLService {
    private AccountManager accountManager = AccountManager.getAccountManager();

    @Override
    public ArrayList<AccountVO> getAccountList() {
        return null;
    }

    @Override
    public ResultMessage addAccount(String name, double amount, String id) {
        return accountManager.addAccount(id, name, amount);
    }

    @Override
    public ArrayList<AccountVO> findAccounts(String keyword) {
        return null;
    }

    @Override
    public AccountVO getAccount(String id) {
        return null;
    }

    @Override
    public ResultMessage deleteAccount(String id) {
        return null;
    }

    @Override
    public ResultMessage modifyAccount(String oldName, String newName) {
        return null;
    }
}
