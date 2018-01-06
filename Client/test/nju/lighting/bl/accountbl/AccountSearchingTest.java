package nju.lighting.bl.accountbl;

import nju.lighting.vo.account.AccountVO;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created on 2017/12/29.
 * Description:
 * @author Liao
 */
public class AccountSearchingTest {
    private AccountManager accountManager = AccountManager.INSTANCE;

    @Test
    public void test0() throws Exception {
        List<AccountVO> accountList = accountManager.findAccounts("33");

        assertEquals(3, accountList.size());
    }
}
