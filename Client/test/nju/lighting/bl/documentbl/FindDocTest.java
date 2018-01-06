package nju.lighting.bl.documentbl;

import nju.lighting.bl.userbl.LoginTestHelper;
import nju.lighting.vo.doc.historydoc.HistoryDocVO;
import org.junit.Before;
import org.junit.Test;
import shared.DocState;
import shared.DocType;
import shared.DocumentFilter;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created on 2017/12/18.
 * Description:
 * @author Liao
 */
public class FindDocTest {
    private static final int ACCOUNT_DOC_SIZE = 4;

    private DocManager manager = DocManager.INSTANCE;
    private DocumentFilter.Builder builder;

    @Before
    public void setUp() throws Exception {
        builder = new DocumentFilter.Builder();
        LoginTestHelper.loginAuthorizedUser();
    }

    @Test
    public void findDocumentsTest0() throws Exception {
        builder.creatorID(LoginTestHelper.AUTHORIZED_USER);
        DocumentFilter filter = builder.build();

        List<HistoryDocVO> docVOList = manager.findDocuments(filter);

        assertEquals(17, docVOList.size());
    }

    @Test
    public void findDocumentsTest1() throws Exception {
        builder.startDate(new Date());

        List<HistoryDocVO> docVOS = manager.findDocuments(builder.build());

        assertEquals(0, docVOS.size());
    }

    @Test
    public void findDocumentsTest2() throws Exception {
        builder.endDate(new Date());

        List<HistoryDocVO> docVOS = manager.findDocuments(builder.build());

        assertEquals(ACCOUNT_DOC_SIZE, docVOS.size());
    }

    @Test
    public void findDocumentsTest3() throws Exception {
        builder.commodity("10W阿拉丁神灯");
        builder.customer("1");

        List<HistoryDocVO> docVOS = manager.findDocuments(builder.build());

        assertEquals(0, docVOS.size());
    }

    @Test
    public void findDocumentsTest4() throws Exception {
        builder.repository("02");

        assertEquals(0, manager.findDocuments(builder.build()).size());
    }

    @Test
    public void findDocumentsTest5() throws Exception {
        builder.docState(DocState.UN_CHECKED);

        List<HistoryDocVO> docVOS = manager.findDocuments(builder.build());

        assertEquals(17, docVOS.size());
    }

    @Test
    public void findDocumentsTest6() throws Exception {
        builder.docType(DocType.ACCOUNT_IN);
        builder.docType(DocType.GIFT);
        builder.docType(DocType.SALES);

        List<HistoryDocVO> docVOS = manager.findDocuments(builder.build());

        assertEquals(8, docVOS.size());
    }
}