
import nju.lighting.data.userdata.UserData;
import nju.lighting.po.user.UserPO;
import org.junit.Test;
import shared.Identity;
import shared.LoginReturnState;
import shared.ResultMessage;
import shared.TwoTuple;

import java.rmi.RemoteException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created on 2017/11/26.
 * Description: UserData数据层模块测试代码
 * 测试通过情况：通过测试
 * @author iznauy
 */
public class UserDataTest {

    private UserData userData = new UserData();

    public UserDataTest() throws RemoteException {
    }


    @Test
    public void insert() throws Exception {
        UserPO userPO = new UserPO("iznauy", "iznauy", "161250000", Identity.GENERAL, false);
        ResultMessage resultMessage = userData.insert(userPO);
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void get() throws Exception {
        UserPO po = userData.get("161250000");
        System.out.println(po);
    }

    @Test
    public void login1() throws Exception {
        String id = "161250000";
        String password = "iznauy";
        TwoTuple<UserPO, LoginReturnState> tuple = userData.login(id, password);
        System.out.println(tuple.t);
        assertEquals(LoginReturnState.SUCCESS, tuple.r);
    }


    @Test
    public void login2() throws Exception {
        String id = "161250000";
        String password = "ziyuan";
        TwoTuple<UserPO, LoginReturnState> tuple = userData.login(id, password);
        assertEquals(null, tuple.t);
        assertEquals(LoginReturnState.INVALID_PASSWORD, tuple.r);
    }

    @Test
    public void update() throws Exception {
        UserPO userPO = new UserPO("iznauy", "iznauy", "161250000", Identity.GENERAL, true);
        ResultMessage resultMessage = userData.update(userPO);
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void delete() throws Exception {
        ResultMessage resultMessage = userData.delete("161250000");
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void getAll() throws Exception {
        List<UserPO> userPOS = userData.getAll();
        for (UserPO userPO: userPOS) {
            System.out.println(userPO);
        }
    }

    @Test
    public void login3() throws Exception {
        String id = "161250222";
        String password = "iznauy";
        TwoTuple<UserPO, LoginReturnState> tuple = userData.login(id, password);
        assertEquals(null, tuple.t);
        assertEquals(LoginReturnState.INVALID_USER_NAME, tuple.r);
    }

}
