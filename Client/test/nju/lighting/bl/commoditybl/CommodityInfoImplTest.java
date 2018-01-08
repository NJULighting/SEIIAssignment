package nju.lighting.bl.commoditybl;

import org.junit.Test;
import shared.ResultMessage;

import static org.junit.Assert.*;

/**
 * Created on 2018/1/8.
 * Description:
 * @author Liao
 */
public class CommodityInfoImplTest {

    private static final String TARGET_COMMODITY_ID = "1-1";
    private static final int MAKE_IT_NEGATIVE = -99999999;
    private static final int NORMAL_ADDITION = 20;

    @Test
    public void addNumberTest1() throws Exception {
        // Try to minus the number of the commodity to a negative number
        CommodityInfo commodityInfo = new CommodityInfoImpl();
        ResultMessage res = commodityInfo.addCommodityNumber(TARGET_COMMODITY_ID, MAKE_IT_NEGATIVE);

        assertEquals(ResultMessage.FAILURE, res);
    }

    @Test
    public void addNumberTest2() throws Exception {
        // Try to minus to a positive number
        CommodityInfo commodityInfo = new CommodityInfoImpl();
        ResultMessage res = commodityInfo.addCommodityNumber(TARGET_COMMODITY_ID, NORMAL_ADDITION);

        assertEquals(ResultMessage.SUCCESS, res);

        // Tear down
        commodityInfo.addCommodityNumber(TARGET_COMMODITY_ID, -NORMAL_ADDITION);
    }
}