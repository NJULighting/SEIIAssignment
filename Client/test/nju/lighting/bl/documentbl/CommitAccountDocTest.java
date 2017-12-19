package nju.lighting.bl.documentbl;

import nju.lighting.bl.userbl.LoginTestHelper;
import nju.lighting.bl.userbl.UserInfo;
import nju.lighting.bl.userbl.UserInfoImpl;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.doc.accountiodoc.AccountIODocVO;
import nju.lighting.vo.doc.accountiodoc.AccountTransferItemVO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import shared.DocType;
import shared.ResultMessage;
import shared.TwoTuple;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Created on 2017/12/18.
 * Description:
 * @author Liao
 */
public class CommitAccountDocTest {
    private static final AccountTransferItemVO ITEM_0 = new AccountTransferItemVO(312.2, "Naive", "1234567");
    private static final AccountTransferItemVO ITEM_1 = new AccountTransferItemVO(312.2, "Excited", "1234567");
    private static final AccountTransferItemVO ITEM_2 = new AccountTransferItemVO(312.2, "Too Young", "1254567");
    private static final List<AccountTransferItemVO> ITEM_LIST = Arrays.asList(ITEM_0, ITEM_1, ITEM_2);

    private DocManager manager = DocManager.INSTANCE;

    @Before
    public void setUp() throws Exception {
        LoginTestHelper.loginAuthorizedUser();
    }

    @Test
    public void commitDocTest0() throws Exception {
        UserInfo userInfo = new UserInfoImpl();
        DocVO docVO = new AccountIODocVO(new Date(), DocType.ACCOUNT_IN,
                "2", userInfo.getIDOfSignedUser(), ITEM_LIST);

        TwoTuple<String, ResultMessage> commitRes = manager.commitDoc(docVO);

        assertEquals(ResultMessage.SUCCESS, commitRes.r);
        assertTrue(commitRes.t.contains("SKD"));
    }

    @Test
    public void commitDocTest1() throws Exception {
        UserInfo userInfo = new UserInfoImpl();
        DocVO docVO = new AccountIODocVO(new Date(), DocType.ACCOUNT_OUT,
                "2", userInfo.getIDOfSignedUser(), ITEM_LIST);

        TwoTuple<String, ResultMessage> res = manager.commitDoc(docVO);

        assertEquals(ResultMessage.SUCCESS, res.r);
        assertTrue(res.t.contains("FKD"));
    }
}