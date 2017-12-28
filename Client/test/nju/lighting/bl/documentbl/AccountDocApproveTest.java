package nju.lighting.bl.documentbl;

import nju.lighting.bl.userbl.LoginTestHelper;
import nju.lighting.bl.userbl.UserInfo;
import nju.lighting.bl.userbl.UserInfoImpl;
import nju.lighting.dataservice.DataFactory;
import nju.lighting.dataservice.documentdataservice.DocDataService;
import nju.lighting.vo.doc.historydoc.HistoryDocVO;
import org.junit.Test;
import shared.DocType;
import shared.ResultMessage;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created on 2017/12/26.
 * Description:
 * @author Liao
 */
public class AccountDocApproveTest {

    private DocDataService dataService;
    private UserInfo userInfo = new UserInfoImpl();

    public AccountDocApproveTest() throws Exception {
        LoginTestHelper.loginAuthorizedUser();
        dataService = DataFactory.getDataBase(DocDataService.class);
    }

    @Test
    public void test0() throws Exception {
        testHelper(DocType.ACCOUNT_IN);
    }

    @Test
    public void test1() throws Exception {
        testHelper(DocType.ACCOUNT_OUT);
    }

    @Test
    public void test2() throws Exception {
        testHelper(DocType.COST);
    }

    @Test
    public void test3() throws Exception {
        testHelper(DocType.GIFT);
    }

    @Test
    public void test4() throws Exception {
        testHelper(DocType.LOSS_AND_GAIN);
    }

    @Test
    public void test5() throws Exception {
        testHelper(DocType.SALES);
    }

    @Test
    public void test6() throws Exception {
        testHelper(DocType.SALES_RETURN);
    }

    @Test
    public void test7() throws Exception {
        testHelper(DocType.STOCK);
    }

    @Test
    public void test8() throws Exception {
        testHelper(DocType.STOCK_RETURN);
    }

    private void testHelper(DocType beTestedType) {
        List<HistoryDocVO> historyDocVOList = ApproveTestHelper.getDocsForApproving(beTestedType);

        DocInfo docInfo = new DocInfoImpl();
        ResultMessage res = docInfo.approveAll(historyDocVOList);
        assertEquals(ResultMessage.SUCCESS, res);
    }
}
