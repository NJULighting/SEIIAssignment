package nju.lighting.bl.commoditybl;

import nju.lighting.bl.userbl.LoginTestHelper;
import nju.lighting.blservice.commodityblservice.CommodityBLService;
import nju.lighting.vo.commodity.CommodityCategoriesTreeVO;
import nju.lighting.vo.commodity.CommodityCategoryVO;
import nju.lighting.vo.commodity.CommodityItemVO;
import org.junit.After;
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
    private static final CommodityItemVO TARGET_COMMODITY = new CommodityItemVO("Frog", "4W", 10, 10,
            20, 10, 20, "第一批", "001", new Date());
    private static final String NONEXISTENT_PATH = "1-6";
    private static final String NOT_LEAF_PATH = "1";
    private static final String RIGHT_PATH = "1-4";
    private static final int CURRENT_INDEX_OF_CATEGORIES = 9; // Remember to increase it after every test
    private static final int NO_ITEMS_CATEGORY_INDEX = 6;

    private CommodityManager manager = CommodityManager.INSTANCE;

    @Before
    public void setUp() throws Exception {
        LoginTestHelper.loginAuthorizedUser();
    }

    @Test
    public void addCommodity() throws Exception {
        ResultMessage res = manager.addCommodity(TARGET_COMMODITY, RIGHT_PATH);

        assertEquals(ResultMessage.SUCCESS, res);
    }

    @Test
    public void addCommodityFailTest0() throws Exception {
        ResultMessage res = manager.addCommodity(TARGET_COMMODITY, NONEXISTENT_PATH);

        assertEquals(ResultMessage.FAILURE, res);
    }

    @Test
    public void addCommodityFailTest1() throws Exception {
        ResultMessage res = manager.addCommodity(TARGET_COMMODITY, NOT_LEAF_PATH);

        assertEquals(ResultMessage.FAILURE, res);
    }

    @Test
    public void addCategoryTest0() throws Exception {
        // Add to the root test
        CommodityCategoriesTreeVO treeVO = manager.getCommodityCategoriesTreeVO();
        CommodityCategoryVO vo = new CommodityCategoryVO(treeVO.getRoot(), "Big Frog Light");

        ResultMessage res = manager.addCategory(vo);

        assertEquals(ResultMessage.SUCCESS, res);
    }

    @Test
    public void addCategoryTest1() throws Exception {
        CommodityCategoriesTreeVO treeVO = manager.getCommodityCategoriesTreeVO();
        CommodityCategoryVO parent = treeVO.getRoot().findChild(3); // Get a category already contains items
        CommodityCategoryVO addition = new CommodityCategoryVO(parent, "Excited Light");

        ResultMessage res = manager.addCategory(addition);

        assertEquals(ResultMessage.FAILURE, res);
    }

    @Test
    public void addCategoryTest2() throws Exception {
        // Construct a nonexistent parent category
        CommodityCategoryVO grandfather = new CommodityCategoryVO(null, 1, "", false);
        CommodityCategoryVO father = new CommodityCategoryVO(grandfather, 2, "Naive", true);
        CommodityCategoryVO addition = new CommodityCategoryVO(father, "Excited");

        ResultMessage res = manager.addCategory(addition);

        assertEquals(ResultMessage.FAILURE, res);
    }

    @Test
    public void addBothTest0() throws Exception {
        // Add category and then add commodity to it
        CommodityCategoriesTreeVO treeVO = manager.getCommodityCategoriesTreeVO();
        CommodityCategoryVO addition = new CommodityCategoryVO(treeVO.getRoot(), "TooYoungLight");

        ResultMessage res1 = manager.addCategory(addition);
        // Update the tree
        treeVO = manager.getCommodityCategoriesTreeVO();
        addition = treeVO.getRoot().findChild(CURRENT_INDEX_OF_CATEGORIES);
        ResultMessage res2 = manager.addCommodity(TARGET_COMMODITY, addition.getPath());

        assertEquals(ResultMessage.SUCCESS, res1);
        assertEquals(ResultMessage.SUCCESS, res2);
    }

    @Test
    public void addBothTest1() throws Exception {
        // Add commodity and then try to add category
        CommodityCategoriesTreeVO treeVO = manager.getCommodityCategoriesTreeVO();
        CommodityCategoryVO parent = treeVO.getRoot().findChild(NO_ITEMS_CATEGORY_INDEX);
        CommodityCategoryVO addition = new CommodityCategoryVO(parent, "NaiveLight");

        ResultMessage res1 = manager.addCommodity(TARGET_COMMODITY, parent.getPath());
        ResultMessage res2 = manager.addCategory(addition);

        assertEquals(ResultMessage.SUCCESS, res1);
        assertEquals(ResultMessage.FAILURE, res2);
    }

    @After
    public void tearDown() throws Exception {
        manager.deleteCommodity("1-4-3");
        manager.deleteCommodity(NO_ITEMS_CATEGORY_INDEX + CommodityBLService.SEPARATOR + "1");
    }
}