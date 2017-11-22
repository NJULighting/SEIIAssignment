package nju.lighting.bl.accountbl;

import nju.lighting.po.account.AccountPO;
import org.junit.Ignore;
import org.junit.Test;
import shared.ResultMessage;

import static org.junit.Assert.*;

/**
 * Created on 2017/11/22.
 * Description:
 * @author Liao
 */
public class AccountManagerTest {
    private AccountManager manager = AccountManager.getAccountManager();

    @Test
    public void getAccountManager() throws Exception {
    }

    @Test
    public void addAccountRepetitionTest() throws Exception {
        assertEquals(manager.addAccount("0001", "whatever", 11.0), ResultMessage.FAILURE);
    }

    @Test
    public void addAccountSucceedTest() throws Exception {
        assertEquals(manager.addAccount("not found", "111", 11.1), ResultMessage.SUCCESS);
    }

    @Test
    public void getAccount() throws Exception {

    }

}