package nju.lighting.bl.documentbl;

import nju.lighting.bl.userbl.LoginTestHelper;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.doc.historydoc.HistoryDocVO;
import org.junit.Before;
import org.junit.Test;
import shared.DocumentFilter;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created on 2017/12/18.
 * Description:
 * @author Liao
 */
public class FindDocTest {
    private static final int ACCOUNT_DOC_SIZE = 2;

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
        DocumentFilter filter =  builder.build();

        List<HistoryDocVO> docVOList = manager.findDocuments(filter);

        assertEquals(ACCOUNT_DOC_SIZE, docVOList.size());
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
}