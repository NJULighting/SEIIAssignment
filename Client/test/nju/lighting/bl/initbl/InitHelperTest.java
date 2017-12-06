package nju.lighting.bl.initbl;

import nju.lighting.bl.userbl.LoginTestHelper;
import nju.lighting.blservice.initblservice.InitializationBLService;
import nju.lighting.vo.InitVO;
import org.junit.Before;
import org.junit.Test;
import shared.ResultMessage;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created on 2017/12/6.
 * Description:
 * @author Liao
 */
public class InitHelperTest {

    @Before
    public void setUp() throws Exception {
        LoginTestHelper.loginAuthorizedUser();
    }

    @Test
    public void createInitTest() throws Exception {
        InitHelper helper = InitHelper.INSTANCE;

        ResultMessage res = helper.createInit();

        assertEquals(ResultMessage.SUCCESS, res);
    }

    @Test
    public void getInitInfoTest() throws Exception {
        InitializationBLService blService = new InitController();

        List<InitVO> voList = blService.getInitInfo();

        assertNotEquals(0, voList.size());
        assertEquals("BbidA", voList.get(0).getUsername());
    }
}