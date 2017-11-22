package nju.lighting.bl.accountbl;

import nju.lighting.dataservice.DataFactory;
import nju.lighting.dataservice.accountdataservice.AccountDataService;
import nju.lighting.po.account.AccountPO;
import nju.lighting.vo.account.AccountVO;
import shared.ResultMessage;

import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.Optional;

/**
 * Created on 2017/11/6.
 * Description:
 * @author Liao
 */
public class AccountManager {
    /* Singleton */
    private static AccountManager accountManager;
    private AccountDataService accountDataService;
    public static AccountManager getAccountManager() {
        return Optional.ofNullable(accountManager).orElseGet(AccountManager::new);
    }

    private AccountManager() {
        try {
            accountDataService = DataFactory.getAccountDataBase();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    /**
     * Add new account
     * @param id id of account(银行账号）
     * @param name name of account(Whatever)
     * @param amount initial amount of this account
     * @return <code>ResultMessage.SUCCESS</code> if add account successfull <br>
     *     <code>ResultMessage.FAILURE</code> if find id repeated <br>
     *         <code>ResultMessage.NETWORK_FAIL</code> if network failed
     */
    ResultMessage addAccount(String id, String name, double amount) {
        try {
            // Check duplication
            AccountPO po = accountDataService.get(id);
            if (po != null) // Find Duplicated
                return ResultMessage.FAILURE;
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        // Create new account and insert to the database
        Account account = new Account(id, amount, name);
        try {
            return accountDataService.insert(account.toPO());
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.NETWORK_FAIL;
        }
    }

    AccountVO getAccount(String id) {
        try {
            AccountPO po = accountDataService.get(id);
            // If not found
            if (po == null)
                return null;
            // Create Account object
            Account target = new Account(po);
            return target.toVO();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }
}
