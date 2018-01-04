package nju.lighting.bl.promotionbl;

import nju.lighting.bl.commoditybl.CommodityInfo;
import nju.lighting.bl.commoditybl.CommodityInfoImpl;
import nju.lighting.bl.userbl.LoginTestHelper;
import nju.lighting.bl.userbl.UserInfo;
import nju.lighting.bl.userbl.UserInfoImpl;
import nju.lighting.vo.UserVO;
import nju.lighting.vo.doc.giftdoc.GiftItemVO;
import nju.lighting.vo.promotion.PromotionVO;
import org.junit.Before;
import org.junit.Test;
import shared.PromotionBuildInfo;
import shared.PromotionType;
import shared.Result;
import shared.ResultMessage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created on 2018/1/4.
 * Description:
 * @author Liao
 */
public class PromotionCommitTest {
    private PromotionManager manager = PromotionManager.INSTANCE;
    private UserInfo userInfo = new UserInfoImpl();

    @Before
    public void setUp() throws Exception {
        LoginTestHelper.loginAuthorizedUser();
    }

    @Test
    public void commitTest0() throws Exception {
        PromotionBuildInfo.Builder builder = new PromotionBuildInfo.Builder("Test", PromotionType.Combo,
                new Date(), new Date(), userInfo.getUserVOByID(userInfo.getIDOfSignedUser()));

        CommodityInfo commodityInfo = new CommodityInfoImpl();

        List<GiftItemVO> itemList = new ArrayList<>();
        itemList.add(new GiftItemVO(commodityInfo.getBasicCommodityItemVO("1-1"), 10));
        itemList.add(new GiftItemVO(commodityInfo.getBasicCommodityItemVO("1-2"), 10));
        itemList.add(new GiftItemVO(commodityInfo.getBasicCommodityItemVO("1-3"), 10));

        builder.off(0.2).goods(itemList).price(70000);
        Result<PromotionVO> result = manager.commit(builder);

        assertTrue(result.hasValue());

        System.out.println(result.getValue());
    }

    @Test
    public void modifyPromotionTest() throws Exception {
        CommodityInfo commodityInfo = new CommodityInfoImpl();
        PromotionVO promotionVO = manager.getPromotionList().get(0);
        promotionVO.setName("Excited promotion");
        promotionVO.getGoods().add(new GiftItemVO(commodityInfo.getBasicCommodityItemVO("1-1"), 10));

        ResultMessage res =  manager.modify(promotionVO);

        assertTrue(res.success());
        assertEquals(2, manager.getPromotionList().size());
    }
}