package nju.lighting.bl.accountbl;

import nju.lighting.bl.logbl.Logger;
import nju.lighting.bl.logbl.MockLogger;
import nju.lighting.dataservice.DataFactory;
import nju.lighting.dataservice.accountdataservice.AccountDataService;
import nju.lighting.po.account.AccountPO;
import nju.lighting.vo.account.AccountVO;
import shared.OPType;
import shared.ResultMessage;

import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created on 2017/11/6.
 * Description:
 * @author Liao
 */
public class AccountManager {
    /* Singleton */
    private static AccountManager accountManager;
    private AccountDataService accountDataService;
    private Logger logger;

    private AccountManager() {
        try {
            accountDataService = DataFactory.getAccountDataBase();
            logger = new MockLogger();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public static AccountManager getAccountManager() {
        return Optional.ofNullable(accountManager).orElseGet(AccountManager::new);
    }

    /**
     * Add new account
     * @param id     id of account(银行账号）
     * @param name   name of account(Whatever)
     * @param amount initial amount of this account
     * @return <code>ResultMessage.SUCCESS</code> if add account successfull <br>
     * <code>ResultMessage.FAILURE</code> if find id repeated or name invalid <br>
     * <code>ResultMessage.NETWORK_FAIL</code> if network failed
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

        // Check name's correctness
        if (!validName(name)) return ResultMessage.FAILURE;

        // Create new account and insert to the database
        Account account = new Account(id, amount, name);
        try {
            ResultMessage res = accountDataService.insert(account.toPO());
            if (res == ResultMessage.SUCCESS)
                logger.add(OPType.ADD, "Account", id);
            return res;
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.NETWORK_FAIL;
        }
    }

    /**
     * Get account with the id input
     * @param id id of the account
     * @return <code>null</code> if no account has this id<br>
     * account with this id if it exists
     */
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

    /**
     * Get all accounts
     * @return A <code>List</code> contains all accounts <br>
     * <code>null</code> if any exception happens
     */
    List<AccountVO> getAccountList() {
        try {
            List<AccountPO> poList = accountDataService.getAll();
            // Transform
            List<AccountVO> res = new ArrayList<>();
            for (AccountPO po : poList) {
                res.add(new Account(po).toVO());
            }
            return res;
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        // Error happen
        return null;
    }

    /**
     * Delete an account according to the id passed
     * @param id id of the account
     * @return <cdoe>SUCCESS</cdoe> if delete success<br>
     * <code>FAILURE</code> if error happens in database<br>
     * <code>NETWORK_FAIL</code> if network failed
     */
    ResultMessage delete(String id) {
        try {
            return accountDataService.delete(id);
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.NETWORK_FAIL;
        }
    }


    ResultMessage rename(String id, String newName) {
        // Check new name's form using regex
        if (!validName(newName))
            return ResultMessage.FAILURE;

        try {
            AccountPO target = accountDataService.get(id);
            if (target.getName().equals(newName))
                return ResultMessage.FAILURE; // Repeated
            // Rename succeed
            target.setName(newName);
            logger.add(OPType.MODIFY, "Account Name", id);
            return ResultMessage.SUCCESS;
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.NETWORK_FAIL;
        }
    }

    private boolean validName(String name) {
        // Check emptiness
        if (name.trim().isEmpty()) return false;

        // Check whether contains illegal character
        String regex = "[`~!@#$%^&*()+=|{}':;,\\[\\].<>/?！￥…（）—【】‘；：”“’。，、？]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(name);
        return !matcher.find();
    }
}
