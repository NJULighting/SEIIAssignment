package nju.lighting.bl.commoditybl;

import nju.lighting.bl.userbl.LoginTestHelper;
import nju.lighting.blservice.commodityblservice.CommodityBLService;
import shared.CommodityBuildInfo;
import nju.lighting.vo.commodity.CommodityCategoriesTreeVO;
import nju.lighting.vo.commodity.CommodityCategoryVO;
import nju.lighting.vo.commodity.CommodityItemVO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import shared.Result;
import shared.ResultMessage;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created on 2017/12/10.
 * Description:
 * @author Liao
 */
public class AdditionTest {
    private static final String NONEXISTENT_PATH = "1-6";
    private static final String NOT_LEAF_PATH = "1";
    private static final String RIGHT_PATH = "1";
    private static final int CURRENT_INDEX_OF_CATEGORIES = 8; // Remember to increase it after every test
    private static final int NO_ITEMS_CATEGORY_INDEX = 5;
    private CommodityManager manager = CommodityManager.INSTANCE;

    private CommodityBuildInfo.CommodityBuilder builder;

    @Before
    public void setUp() throws Exception {
        LoginTestHelper.loginAuthorizedUser();
    }

    @Test
    public void addCommodity() throws Exception {
        builder = new CommodityBuildInfo.CommodityBuilder(RIGHT_PATH);
        setUpCommodity();
        Result<CommodityItemVO> result = manager.addCommodity(builder);

        assertTrue(result.hasValue());
        System.out.println(result.getValue());
    }

    @Test
    public void addCommodityFailTest0() throws Exception {
        builder = new CommodityBuildInfo.CommodityBuilder(NONEXISTENT_PATH);
        Result<CommodityItemVO> result = manager.addCommodity(builder);

        assertFalse(result.hasValue());
    }

    @Test
    public void addCommodityFailTest1() throws Exception {
        builder = new CommodityBuildInfo.CommodityBuilder(NOT_LEAF_PATH);
        Result<CommodityItemVO> result = manager.addCommodity(builder);

        assertFalse(result.hasValue());
    }

    @Test
    public void addCategoryTest0() throws Exception {
        // Add to the root test
        CommodityCategoriesTreeVO treeVO = manager.getCommodityCategoriesTreeVO();
        builder = new CommodityBuildInfo.CommodityBuilder(treeVO.getRoot());
        setUpCommodity();

        Result<CommodityItemVO> result = manager.addCommodity(builder);

        assertFalse(result.hasValue());
    }

    @Test
    public void addCategoryTest1() throws Exception {
        CommodityCategoriesTreeVO treeVO = manager.getCommodityCategoriesTreeVO();
        CommodityCategoryVO parent = treeVO.getRoot().findChild(3); // Get a category already contains items
        CommodityCategoryVO addition = new CommodityCategoryVO(parent, "Excited Light");

        ResultMessage res = manager.addCategory(addition).getResultMessage();

        assertEquals(ResultMessage.FAILURE, res);
    }

    @Test
    public void addCategoryTest2() throws Exception {
        // Construct a nonexistent parent category
        CommodityCategoryVO grandfather = new CommodityCategoryVO(null, 1, "", false);
        CommodityCategoryVO father = new CommodityCategoryVO(grandfather, 2, "Naive", true);
        CommodityCategoryVO addition = new CommodityCategoryVO(father, "Excited");

        ResultMessage res = manager.addCategory(addition).getResultMessage();

        assertEquals(ResultMessage.FAILURE, res);
    }

    @Test
    public void addBothTest0() throws Exception {
        // Add category and then add commodity to it
        CommodityCategoriesTreeVO treeVO = manager.getCommodityCategoriesTreeVO();
        CommodityCategoryVO addition = new CommodityCategoryVO(treeVO.getRoot(), "TooYoungLight");

        Result<CommodityCategoryVO> addResult = manager.addCategory(addition);
        ResultMessage res1 = addResult.getResultMessage();
        System.out.println(addResult.getValue());
        // Update the tree
        treeVO = manager.getCommodityCategoriesTreeVO();
        addition = treeVO.getRoot().findChild(CURRENT_INDEX_OF_CATEGORIES);

        builder = new CommodityBuildInfo.CommodityBuilder(addition);
        setUpCommodity();
        Result<CommodityItemVO> result = manager.addCommodity(builder);

        assertTrue(result.hasValue());
        assertEquals(ResultMessage.SUCCESS, res1);
    }

    @Test
    public void addBothTest1() throws Exception {
        // Add commodity and then try to add category
        CommodityCategoriesTreeVO treeVO = manager.getCommodityCategoriesTreeVO();
        CommodityCategoryVO parent = treeVO.getRoot().findChild(NO_ITEMS_CATEGORY_INDEX);
        CommodityCategoryVO addition = new CommodityCategoryVO(parent, "NaiveLight");

        // Add commodity
        builder = new CommodityBuildInfo.CommodityBuilder(parent);
        setUpCommodity();
        Result<CommodityItemVO> result = manager.addCommodity(builder);

        // Add category
        ResultMessage res2 = manager.addCategory(addition).getResultMessage();

        assertTrue(result.hasValue());
        assertEquals(ResultMessage.FAILURE, res2);
    }

    @After
    public void tearDown() throws Exception {
        manager.deleteCommodity("1-4-3");
        manager.deleteCommodity(NO_ITEMS_CATEGORY_INDEX + CommodityBLService.SEPARATOR + "1");
    }

    private void setUpCommodity() {
        builder.name("Frog").modelNumber("4W").repCount(10).inPrice(50).sellPrice(50)
                .recentInPrice(100).recentSellPrice(100).batch("第一批").batchNumber("001").dateOfProduction(new Date());
    }
}