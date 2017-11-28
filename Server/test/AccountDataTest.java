import nju.lighting.data.accountdata.AccountData;
import nju.lighting.po.account.AccountLogPO;
import nju.lighting.po.account.AccountPO;
import org.junit.Test;
import shared.AccountChangeType;
import shared.ResultMessage;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created on 2017/11/26.
 * Description: Account模块测试代码
 * 测试通过情况：通过全部测试
 * @author iznauy
 */
public class AccountDataTest {

    private AccountData accountData = new AccountData();

    @Test
    public void insert() throws RemoteException {
        AccountPO accountPO = new AccountPO("161250068", "BbidA的校园卡", 2333, new ArrayList<>());
        ResultMessage resultMessage = accountData.insert(accountPO);
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void get() throws Exception {
        AccountPO accountPO = accountData.get("161250068");
        System.out.println(accountPO == null);
        System.out.println(accountPO);
    }

    @Test
    public void getAll() throws Exception {
        List<AccountPO> accountPOS = accountData.getAll();
        for (AccountPO accountPO: accountPOS) {
            System.out.println(accountPO);
        }
    }

    @Test
    public void delete() throws Exception {
        String id = "161250068";
        ResultMessage resultMessage = accountData.delete(id);
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void update() throws Exception {
        AccountPO accountPO0 = new AccountPO("161250068", "BbidA的校园卡", 2333, new ArrayList<>());
        accountData.insert(accountPO0);
        AccountPO accountPO = new AccountPO("161250068", "BbidA的校园卡", 6666, new ArrayList<>());
        ResultMessage message = accountData.update(accountPO);
        assertEquals(ResultMessage.SUCCESS, message);
    }

    @Test
    public void add() throws Exception {
        AccountPO accountPO0 = new AccountPO("161250068", "BbidA的校园卡", 2333, new ArrayList<>());
        accountData.insert(accountPO0);
        AccountLogPO logPO = new AccountLogPO(new Date(), 100, 2433, AccountChangeType.IN, "161250068");
        ResultMessage resultMessage = accountData.add(logPO);
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }
}
