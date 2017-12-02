package nju.lighting.bl.userbl;

import nju.lighting.blservice.userblservice.UserBLService;
import nju.lighting.vo.UserVO;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created on 2017/12/1.
 * Description:
 * @author Liao
 */
public class GetUserTest {

    private UserBLService userBLService = new UserController();

    @Test
    public void getUserListTest() throws Exception {
        List<UserVO> vos = userBLService.getUserList();
        assertEquals(3, vos.size());
        assertEquals("161250068", vos.get(2).getID());
    }
}
