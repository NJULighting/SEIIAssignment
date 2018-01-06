package nju.lighting.bl.accountbl;

import nju.lighting.bl.userbl.LoginTestHelper;
import nju.lighting.vo.account.AccountVO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import shared.ResultMessage;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created on 2017/11/22.
 * Description:
 * @author Liao
 */
public class AccountManagerTest {
    private AccountManager manager = AccountManager.getInstance();

    @Before
    public void setUp() throws Exception {
        LoginTestHelper.loginAuthorizedUser();
    }

    @Test
    public void getAllAccounts() throws Exception {
        List<AccountVO> voList = manager.getAccountList();
        assert voList != null;
        assertEquals(13, voList.size());
        assertNotNull(voList.get(0).getAccountLogs());
        assertEquals("BbidA的建设银行卡", voList.get(1).getName());
    }

    @Test
    public void addAccountRepetitionTest() throws Exception {
        assertEquals(manager.addAccount("0655367629469011294", "whatever", 11.0), ResultMessage.DUPLICATE);
    }

    @Test
    public void addAccountSucceedTest() throws Exception {
        assertEquals(manager.addAccount("161250068", "111", 11.1), ResultMessage.SUCCESS);
    }

    @Test
    public void addAccountInvalidNameTest() throws Exception {
        assertEquals(manager.addAccount("123456789", "$jsdf", 11.1), ResultMessage.INVALID_NAME);
    }

    @Test
    public void getAccountFailedTest() throws Exception {
        assertNull(manager.getAccount("11234456"));
    }

    @Test
    public void getAccountSucceedTest() throws Exception {
        AccountVO target = manager.getAccount("284983325");
        assertEquals("0655367629469011294", target.getId());
        assertTrue(target.getAccountLogs().isEmpty());
    }

    @Test
    public void renameTest1() throws Exception {
        assertEquals(manager.rename("284983325", "%sj@ "), ResultMessage.INVALID_NAME);
    }

    // Duplication test
    @Test
    public void renameTest2() throws Exception {
        assertEquals(manager.rename("284983325", "BbidA的建设银行卡"), ResultMessage.DUPLICATE);
    }

    @Test
    public void renameTest3() throws Exception {
        assertEquals(manager.rename("284983325", "Excited"), ResultMessage.SUCCESS);
    }

    @After
    public void tearDown() throws Exception {
        manager.delete("161250068");
        manager.rename("284983325", "BbidA的建设银行卡");
    }
}