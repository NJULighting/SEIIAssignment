package nju.lighting.bl.commoditybl;

import nju.lighting.bl.userbl.LoginTestHelper;
import nju.lighting.vo.commodity.CommodityCategoriesTreeVO;
import nju.lighting.vo.commodity.CommodityCategoryVO;
import nju.lighting.vo.commodity.CommodityItemVO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import shared.ResultMessage;

import static org.junit.Assert.assertEquals;

/**
 * Created on 2017/12/12.
 * Description:
 * @author Liao
 */
public class ModificationTest {
    private static final String TARGET_COMMODITY_ID = "1-90";
    private static final int TARGET_CATEGORY_ID = 3;
    private static final String TARGET_CATEGORY_NEW_NAME = "LifeExperience";

    private CommodityManager manager = CommodityManager.INSTANCE;
    private CommodityItemVO oldData = manager.findCommodityVOById(TARGET_COMMODITY_ID);
    private CommodityCategoryVO oldCategoryData = manager.getCommodityCategoriesTreeVO().getRoot().findChild(TARGET_CATEGORY_ID);

    @Before
    public void setUp() throws Exception {
        LoginTestHelper.loginAuthorizedUser();
    }

    @Test
    public void modifyCommodity() throws Exception {
        CommodityItemVO changedData = manager.findCommodityVOById(TARGET_COMMODITY_ID);
        changedData.setName("TooSimpleLight");
        changedData.setSellPrice(66.66);

        ResultMessage res = manager.modifyCommodity(changedData);

        assertEquals(ResultMessage.SUCCESS, res);
    }

    @Test
    public void changeCategoryName() throws Exception {
        CommodityCategoriesTreeVO treeVO = manager.getCommodityCategoriesTreeVO();
        CommodityCategoryVO changeData = treeVO.getRoot().findChild(TARGET_CATEGORY_ID);
        changeData.setName(TARGET_CATEGORY_NEW_NAME);

        ResultMessage res = manager.changeCategoryName(changeData);

        assertEquals(ResultMessage.SUCCESS, res);
    }

    @After
    public void tearDown() throws Exception {
        manager.modifyCommodity(oldData);
        manager.changeCategoryName(oldCategoryData);
    }
}