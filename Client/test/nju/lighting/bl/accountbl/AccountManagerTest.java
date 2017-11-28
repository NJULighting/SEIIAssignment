package nju.lighting.bl.accountbl;

import nju.lighting.vo.account.AccountVO;
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

    @Test
    public void getAllAccounts() throws Exception {
        List<AccountVO> voList = manager.getAccountList();
        assert voList != null;
        assertEquals(5, voList.size());
        assertNotNull(voList.get(0).getAccountLogs());
        assertEquals("BbidA的校园卡", voList.get(1).getName());
    }

    @Test
    public void addAccountRepetitionTest() throws Exception {
        assertEquals(manager.addAccount("0001", "whatever", 11.0), ResultMessage.DUPLICATE);
    }

    @Test
    public void addAccountSucceedTest() throws Exception {
        assertEquals(manager.addAccount("not found", "111", 11.1), ResultMessage.SUCCESS);
    }

    @Test
    public void addAccountInvalidNameTest() throws Exception {
        assertEquals(manager.addAccount("not found", "$jsdf", 11.1), ResultMessage.INVALID_NAME);
    }

    @Test
    public void getAccountFailedTest() throws Exception {
        assertNull(manager.getAccount("not found"));
    }

    @Test
    public void getAccountSucceedTest() throws Exception {
        AccountVO target = manager.getAccount("0001");
        assertEquals("0001", target.getId());
        assertEquals("Frog Account", target.getName());
        assertTrue(target.getAccountLogs().isEmpty());
    }

    @Test
    public void deleteFailTest() throws Exception {
        assertEquals(manager.delete(""), ResultMessage.FAILURE);
        assertEquals(manager.delete("Excited"), ResultMessage.SUCCESS);
    }

    @Test
    public void renameTest1() throws Exception {
        assertEquals(manager.rename("0001", "%sj@ "), ResultMessage.INVALID_NAME);
    }

    @Test
    public void renameTest2() throws Exception {
        assertEquals(manager.rename("0001", "Frog Account"), ResultMessage.DUPLICATE);
    }

    @Test
    public void renameTest3() throws Exception {
        assertEquals(manager.rename("0001", "Excited"), ResultMessage.SUCCESS);
    }
}