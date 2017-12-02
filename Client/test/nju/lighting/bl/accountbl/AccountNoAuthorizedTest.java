package nju.lighting.bl.accountbl;


import nju.lighting.bl.userbl.LoginHelper;
import org.junit.Before;
import org.junit.Test;
import shared.ResultMessage;

import static org.junit.Assert.*;
/**
 * Created on 2017/12/1.
 * Description: This test is based on that the user is not authorized
 * @author Liao
 */
public class AccountNoAuthorizedTest {
    private AccountManager manager = AccountManager.getInstance();

    @Before
    public void setUp() throws Exception {
        LoginHelper.INSTANCE.login("1611200xx", "9999");
    }

    @Test
    public void addAccountTest() throws Exception {

        ResultMessage res = manager.addAccount("161120038", "whatever", 1111);

        assertEquals(ResultMessage.FAILURE, res);
    }

    @Test
    public void renameAccountTest() throws Exception {
        ResultMessage res = manager.rename("161250068", "Yeah");

        assertEquals(ResultMessage.FAILURE, res);
    }

    @Test
    public void deleteAccountTest() throws Exception {
        ResultMessage res = manager.delete("161250068");

        assertEquals(ResultMessage.FAILURE, res);
    }
}
