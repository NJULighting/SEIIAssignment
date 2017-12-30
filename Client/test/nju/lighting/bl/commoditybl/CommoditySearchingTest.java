package nju.lighting.bl.commoditybl;

import nju.lighting.vo.commodity.CommodityItemVO;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created on 2017/12/12.
 * Description:
 * @author Liao
 */
public class CommoditySearchingTest {
    private static final CommodityItemVO TARGET_COMMODITY = new CommodityItemVO("Frog", "4W", 10, 10,
            20, 10, 20, "第一批", "001", new Date());
    private static final String RIGHT_COMMODITY_ID = "1-4-2";
    private static final String WRONG_COMMODITY_ID = "1-4-3-2";
    private static final String COMMODITY_NAME = "Frog";
    private static final String NONEXISTENT_COMMODITY_NAME = "Excited";
    private static final int CATEGORY_ID = 3;
    private static final int COMMODITY_NUMBER = 2;
    private static final int NONEXISTENT_CATEGORY_ID = 200;

    private CommodityManager manager = CommodityManager.INSTANCE;

    @Test
    public void findCommodityByCategoryTest0() throws Exception {
        List<CommodityItemVO> voList = manager.findCommodityByCategory(CATEGORY_ID);

        assertEquals(COMMODITY_NUMBER, voList.size());
    }

    @Test
    public void findCommodityByCategoryTest1() throws Exception {
        List<CommodityItemVO> voList = manager.findCommodityByCategory(NONEXISTENT_CATEGORY_ID);

        assertTrue(voList.isEmpty());
    }

    @Test
    public void findCommodityVOByNameTest0() throws Exception {
        List<CommodityItemVO> voList = manager.findCommodityVOByName(COMMODITY_NAME);

        assertEquals(10, voList.size());
    }

    @Test
    public void findCommodityVOByNameTest1() throws Exception {
        List<CommodityItemVO> voList = manager.findCommodityVOByName(NONEXISTENT_COMMODITY_NAME);

        assertTrue(voList.isEmpty());
    }

    @Test
    public void findCommodityVOByIdTest0() throws Exception {
        // Right id test
        CommodityItemVO target = manager.findCommodityVOById(RIGHT_COMMODITY_ID);

        assertEquals(TARGET_COMMODITY.getBatch(), target.getBatch());
        assertEquals(TARGET_COMMODITY.getName(), target.getName());
    }

    @Test
    public void findCommodityVOByIdTest1() throws Exception {
        // Wrong id test
        CommodityItemVO target = manager.findCommodityVOById(WRONG_COMMODITY_ID);

        assertNull(target);
    }

    @Test
    public void searchingTest0() throws Exception {
        searchingHelper("W", 504);
    }

    @Test
    public void searchingTest1() throws Exception {
        searchingHelper("10", 7);
    }

    private void searchingHelper(String keyword, int expectedSize) {
        List<CommodityItemVO> itemList = manager.searchCommodity(keyword);

        itemList.forEach(System.out::println);
        assertEquals(expectedSize, itemList.size());
    }
}