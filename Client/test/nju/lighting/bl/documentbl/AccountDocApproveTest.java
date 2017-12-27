package nju.lighting.bl.documentbl;

import nju.lighting.bl.userbl.LoginTestHelper;
import nju.lighting.bl.userbl.UserInfo;
import nju.lighting.bl.userbl.UserInfoImpl;
import nju.lighting.dataservice.DataFactory;
import nju.lighting.dataservice.documentdataservice.DocDataService;
import nju.lighting.po.doc.DocPO;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.doc.historydoc.HistoryDocVO;
import org.junit.Assert;
import org.junit.Test;
import shared.DocState;
import shared.DocType;
import shared.ResultMessage;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
        DocFactory docFactory = DocFactory.INSTANT;
        List<DocPO> poList = dataService.findByType(DocType.ACCOUNT_IN);
        List<DocVO> voList = poList.stream().map(po -> docFactory.poToDoc(po).toVO()).collect(Collectors.toList());

        List<HistoryDocVO> historyDocVOList = voList.stream()
                .map(vo -> new HistoryDocVO(userInfo.getUserVOByID(vo.getCreatorId()), vo,
                        "test", DocState.APPROVAL, new Date(),
                        userInfo.getUserVOByID(userInfo.getIDOfSignedUser())))
                .collect(Collectors.toList());

        DocInfo docInfo = new DocInfoImpl();
        ResultMessage res = docInfo.approveAll(historyDocVOList);
        Assert.assertEquals(ResultMessage.SUCCESS, res);
    }
}
