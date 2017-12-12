package nju.lighting.bl.commoditybl;

import nju.lighting.bl.userbl.LoginTestHelper;
import nju.lighting.vo.commodity.CommodityItemVO;
import org.junit.Before;
import org.junit.Test;
import shared.ResultMessage;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created on 2017/12/10.
 * Description:
 * @author Liao
 */
public class AdditionTest {
    private CommodityManager manager = CommodityManager.INSTANCE;

    @Before
    public void setUp() throws Exception {
        LoginTestHelper.loginAuthorizedUser();
    }

    @Test
    public void addCommodity() throws Exception {
        ResultMessage res = manager.addCommodity(new CommodityItemVO("Frog", "4W", 10, 10, 20, 10, 20, "第一批", "001", new Date()), "1-4");

        assertEquals(ResultMessage.SUCCESS, res);
    }

    @Test
    public void addCategory() throws Exception {
    }

}