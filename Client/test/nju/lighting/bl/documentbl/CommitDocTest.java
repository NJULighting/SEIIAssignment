package nju.lighting.bl.documentbl;

import nju.lighting.bl.accountbl.AccountInfo;
import nju.lighting.bl.accountbl.AccountInfoImpl;
import nju.lighting.bl.commoditybl.CommodityInfo;
import nju.lighting.bl.commoditybl.CommodityInfoImpl;
import nju.lighting.bl.userbl.LoginTestHelper;
import nju.lighting.bl.userbl.UserInfo;
import nju.lighting.bl.userbl.UserInfoImpl;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.doc.accountiodoc.AccountIODocVO;
import nju.lighting.vo.doc.accountiodoc.AccountTransferItemVO;
import nju.lighting.vo.doc.alertdoc.AlertDocItemVO;
import nju.lighting.vo.doc.alertdoc.AlertDocVO;
import nju.lighting.vo.doc.costdoc.CostDocItemVO;
import nju.lighting.vo.doc.costdoc.CostDocVO;
import nju.lighting.vo.doc.giftdoc.GiftDocVO;
import nju.lighting.vo.doc.giftdoc.GiftItemVO;
import nju.lighting.vo.doc.lossandgaindoc.LossAndGainDocItemVO;
import nju.lighting.vo.doc.lossandgaindoc.LossAndGainDocVO;
import nju.lighting.vo.doc.salesdoc.SalesDocItemVO;
import nju.lighting.vo.doc.salesdoc.SalesDocVO;
import nju.lighting.vo.doc.salesdoc.SalesReturnDocVO;
import nju.lighting.vo.doc.stockdoc.StockDocItemVO;
import nju.lighting.vo.doc.stockdoc.StockDocVO;
import nju.lighting.vo.doc.stockdoc.StockReturnDocVO;
import org.junit.Before;
import org.junit.Test;
import shared.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created on 2017/12/18.
 * Description:
 * @author Liao
 */
public class CommitDocTest {

    private static final List<AccountTransferItemVO> ITEM_LIST = Arrays.asList(ITEM_0, ITEM_1, ITEM_2);
    private static String id = "0033029935110547923";
    private static final AccountTransferItemVO ITEM_0 = new AccountTransferItemVO(312.2, "Naive", id);
    private static final AccountTransferItemVO ITEM_1 = new AccountTransferItemVO(312.2, "Excited", id);
    private static final AccountTransferItemVO ITEM_2 = new AccountTransferItemVO(312.2, "Too Young", id);
    private DocManager manager = DocManager.INSTANCE;
    private UserInfo userInfo = new UserInfoImpl();
    private CommodityInfo commodityInfo = new CommodityInfoImpl();

    @Before
    public void setUp() throws Exception {
        LoginTestHelper.loginAuthorizedUser();
    }

    // Account doc
    @Test
    public void commitDocTest0() throws Exception {
        DocVO docVO = new AccountIODocVO(new Date(), DocType.ACCOUNT_IN,
                "2", userInfo.getIDOfSignedUser(), ITEM_LIST);

        TwoTuple<ResultMessage, String> commitRes = manager.commitDoc(docVO);

        assertEquals(ResultMessage.SUCCESS, commitRes.t);
        assertTrue(commitRes.r.contains("SKD"));
    }

    // Account doc
    @Test
    public void commitDocTest1() throws Exception {
        DocVO docVO = new AccountIODocVO(new Date(), DocType.ACCOUNT_OUT,
                "2", userInfo.getIDOfSignedUser(), ITEM_LIST);

        TwoTuple<ResultMessage, String> res = manager.commitDoc(docVO);

        assertEquals(ResultMessage.SUCCESS, res.t);
        assertTrue(res.r.contains("FKD"));
    }

    @Test
    public void commitDocTest2() throws Exception {
        List<AlertDocItemVO> itemVOList = new ArrayList<>();
        itemVOList.add(new AlertDocItemVO(commodityInfo.getBasicCommodityItemVO("1-1"), 4));
        itemVOList.add(new AlertDocItemVO(commodityInfo.getBasicCommodityItemVO("1-2"), 2));
        DocVO docVO = new AlertDocVO(userInfo.getIDOfSignedUser(), new Date(), itemVOList, "Excited");

        testResult("BJD", docVO);
    }

    @Test
    public void commitDocTest3() throws Exception {
        List<CostDocItemVO> itemList = new ArrayList<>();
        itemList.add(new CostDocItemVO(CostDocItemType.ACCOMMODATION, 666.6, "Naive"));
        itemList.add(new CostDocItemVO(CostDocItemType.BUSINESS_TRIP, 666.6, "Excited"));

        AccountInfo accountInfo = new AccountInfoImpl();
        DocVO docVO = new CostDocVO(new Date(), userInfo.getIDOfSignedUser(),
                accountInfo.getAccountByID(id), itemList);

        testResult("XJFYD", docVO);
    }

    @Test
    public void commitDocTest4() throws Exception {
        List<GiftItemVO> itemList = new ArrayList<>();

        itemList.add(new GiftItemVO(commodityInfo.getBasicCommodityItemVO("1-1"), 5));
        itemList.add(new GiftItemVO(commodityInfo.getBasicCommodityItemVO("1-2"), 5));

        DocVO docVO = new GiftDocVO(new Date(), userInfo.getIDOfSignedUser(), itemList, 1, 0);

        testResult("LPD", docVO);
    }

    @Test
    public void commitDocTest5() throws Exception {
        List<LossAndGainDocItemVO> itemList = new ArrayList<>();

        itemList.add(new LossAndGainDocItemVO(commodityInfo.getBasicCommodityItemVO("1-1"), 4, LossAndGainItemType.GAIN));
        itemList.add(new LossAndGainDocItemVO(commodityInfo.getBasicCommodityItemVO("1-2"), 4, LossAndGainItemType.LOSS));

        DocVO docVO = new LossAndGainDocVO(new Date(), userInfo.getIDOfSignedUser(), itemList, "TooYoung");

        testResult("BSBYD", docVO);
    }

    @Test
    public void commitDocTest6() throws Exception {
        List<SalesDocItemVO> itemList = new ArrayList<>();

        itemList.add(new SalesDocItemVO(10, "TooSimple", commodityInfo.getBasicCommodityItemVO("1-1")));
        itemList.add(new SalesDocItemVO(10, "TooSimple", commodityInfo.getBasicCommodityItemVO("1-2")));

        DocVO docVO = new SalesDocVO(new Date(), userInfo.getIDOfSignedUser(), 1, userInfo.getIDOfSignedUser(), "01",
                "Anyway", 0.2, 100, itemList);

        DocVO returnDocVO = new SalesReturnDocVO(new Date(), userInfo.getIDOfSignedUser(), 1, userInfo.getIDOfSignedUser(),
                "01", "Whatever", 0.1, 0, itemList);

        testResult("XSD", docVO);
        testResult("XSTHD", returnDocVO);
    }

    @Test
    public void commitDocTest7() throws Exception {
        List<StockDocItemVO> itemList = new ArrayList<>();

        itemList.add(new StockDocItemVO(commodityInfo.getBasicCommodityItemVO("1-1"), 20, "Excited"));
        itemList.add(new StockDocItemVO(commodityInfo.getBasicCommodityItemVO("1-2"), 20, "Excited"));

        DocVO docVO = new StockDocVO(new Date(), userInfo.getIDOfSignedUser(), "1", "01", "Naive", itemList);
        DocVO returnDocVO = new StockReturnDocVO(new Date(), userInfo.getIDOfSignedUser(), "1", "01", "Excited", itemList);

        testResult("JHD", docVO);
        testResult("JHTHD", returnDocVO);
    }

    private void testResult(String docId, DocVO docVO) {
        TwoTuple<ResultMessage, String> res = manager.commitDoc(docVO);

        assertEquals(ResultMessage.SUCCESS, res.t);
        assertTrue(res.r.contains(docId));
    }
}