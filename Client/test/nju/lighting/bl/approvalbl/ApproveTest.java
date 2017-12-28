package nju.lighting.bl.approvalbl;

import nju.lighting.bl.documentbl.ApproveTestHelper;
import nju.lighting.blservice.approvalblservice.ApprovalBLService;
import nju.lighting.vo.doc.accountiodoc.AccountIODocVO;
import nju.lighting.vo.doc.accountiodoc.AccountTransferItemVO;
import nju.lighting.vo.doc.historydoc.HistoryDocVO;
import org.junit.Test;
import shared.DocType;
import shared.ResultMessage;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created on 2017/12/28.
 * Description:
 * @author Liao
 */
public class ApproveTest {
    private ApprovalBLService blService = new ApprovalController();

    @Test
    public void test0() throws Exception {
        List<HistoryDocVO> docVOS = ApproveTestHelper.getDocsForApproving();

        ResultMessage res = blService.approveAll(docVOS);

        assertEquals(ResultMessage.SUCCESS, res);
    }

    @Test
    public void test1() throws Exception {
        List<HistoryDocVO> docVOS = ApproveTestHelper.getDocsForApproving();

        ResultMessage res = ResultMessage.FAILURE;
        if (docVOS.stream().allMatch(vo -> blService.reject(vo) == ResultMessage.SUCCESS))
            res = ResultMessage.SUCCESS;

        assertEquals(ResultMessage.SUCCESS, res);
    }

    @Test
    public void test2() throws Exception {
        List<HistoryDocVO> docVOS = ApproveTestHelper.getDocsForSaving(DocType.ACCOUNT_IN);
        ResultMessage res = ResultMessage.FAILURE;

        for (HistoryDocVO docVO : docVOS) {
            AccountIODocVO accountIODocVO = (AccountIODocVO) docVO.getDocVO();
            List<AccountTransferItemVO> itemList = accountIODocVO.getTransferAccountList();
            itemList.add(new AccountTransferItemVO(800, "额外添加测试", itemList.get(0).getAccountID()));

            if (blService.save(docVO) != ResultMessage.SUCCESS)
                break;
            res = ResultMessage.SUCCESS;
        }
        assertEquals(ResultMessage.SUCCESS, res);
    }
}