package nju.lighting.bl.accountbl;

import nju.lighting.vo.account.AccountVO;
import org.junit.Ignore;
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
        assertEquals(voList.size(), 4);
        assertNotNull(voList.get(0).getAccountLogs());
        assertEquals(voList.get(1).getName(), "GMT的校园卡");
    }

    @Test
    public void addAccountRepetitionTest() throws Exception {
        assertEquals(manager.addAccount("284983325", "BbidA的建设银行卡", 11.0), ResultMessage.DUPLICATE);
    }

    @Test @Ignore
    public void addAccountSucceedTest() throws Exception {
        assertEquals(manager.addAccount("20177102", "不负泽任的银行卡", 11111.1), ResultMessage.SUCCESS);
    }

    @Test
    public void addAccountInvalidNameTest() throws Exception {
        assertEquals(manager.addAccount("whatever", "$jsdf", 11.1), ResultMessage.INVALID_NAME);
    }

    @Test
    public void getAccountFailedTest() throws Exception {
        assertNull(manager.getAccount("28498332"));
    }

    @Test
    public void getAccountSucceedTest() throws Exception {
        AccountVO target = manager.getAccount("284983325");
        assertEquals("284983325", target.getId());
        assertEquals("BbidA的建设银行卡", target.getName());
        assertTrue(target.getAccountLogs().isEmpty());
    }

    @Test
    public void deleteFailTest() throws Exception {
        assertEquals(manager.delete("2423"), ResultMessage.FAILURE);
        assertEquals(manager.delete("20177102"), ResultMessage.SUCCESS);
    }

    @Test
    public void renameTest1() throws Exception {
        assertEquals(manager.rename("284983325", "%sj@ "), ResultMessage.INVALID_NAME);
    }

    @Test
    public void renameTest2() throws Exception {
        assertEquals(manager.rename("284983325", "BbidA的建设银行卡"), ResultMessage.DUPLICATE);
    }

    @Test
    public void renameTest3() throws Exception {
        assertEquals(manager.rename("284983325", "BbidA的建设银行卡Excited"), ResultMessage.SUCCESS);
    }

    @Test
    public void renameTest4() throws Exception {
        assertEquals(manager.rename("0001", "whatever"), ResultMessage.FAILURE);
    }
}