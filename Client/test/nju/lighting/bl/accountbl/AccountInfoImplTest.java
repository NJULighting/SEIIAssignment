package nju.lighting.bl.accountbl;

import org.junit.Test;
import shared.ResultMessage;

import static org.junit.Assert.*;

/**
 * Created on 2018/1/8.
 * Description:
 * @author Liao
 */
public class AccountInfoImplTest {
    private static final String TARGET_ACCOUNT = "0033029935110547923";
    private static final double MAKE_IT_NEGATIVE = -Double.MAX_VALUE;
    private static final double NORMAL_ADDITION = 100;

    @Test
    public void addAmountTest0() throws Exception {
        // Try to add a negative number to make the amount negative
        AccountInfo accountInfo = new AccountInfoImpl();
        ResultMessage res = accountInfo.addAmount(TARGET_ACCOUNT, MAKE_IT_NEGATIVE);

        assertEquals(ResultMessage.FAILURE, res);
    }

    @Test
    public void addAmountTest1() throws Exception {
        // Add a normal number test
        AccountInfo accountInfo = new AccountInfoImpl();
        ResultMessage res = accountInfo.addAmount(TARGET_ACCOUNT, NORMAL_ADDITION);

        assertEquals(ResultMessage.SUCCESS, res);

        // Tear down
        res = accountInfo.addAmount(TARGET_ACCOUNT, -NORMAL_ADDITION);
        assertEquals(ResultMessage.SUCCESS, res);
    }
}