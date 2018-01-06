package nju.lighting.bl.documentbl;

import nju.lighting.bl.userbl.LoginTestHelper;
import nju.lighting.vo.doc.historydoc.HistoryDocVO;
import nju.lighting.vo.viewtables.BusinessHistoryItemVO;
import nju.lighting.vo.viewtables.SalesDetailItemVO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import shared.DocState;
import shared.DocType;
import shared.DocumentFilter;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created on 2017/12/25.
 * Description:
 * @author Liao
 */
public class FindSaleRecordsTest {

    private DocManager manager = DocManager.INSTANCE;
    private DocumentFilter.Builder builder;


    @Before
    public void setUp() throws Exception {
        builder = new DocumentFilter.Builder();
        LoginTestHelper.loginAuthorizedUser();
    }

    @Test
    public void test0() throws Exception {
        builder.commodity("LED5W灯泡").customer("1");

        List<SalesDetailItemVO> itemList = manager.findSaleRecords(builder.build());

        assertEquals(0, itemList.size());
    }

    @Test
    public void test1() throws Exception {
        builder.creatorID(LoginTestHelper.AUTHORIZED_USER);

        assertEquals(8, manager.findSaleRecords(builder.build()).size());
    }

    @Test
    public void test2() throws Exception {
        builder.docType(DocType.ACCOUNT_IN).docType(DocType.SALES);

        List<BusinessHistoryItemVO> itemList = manager.findBusinessHistory(builder.build());

        assertEquals(10, itemList.size());
    }

    @Test
    public void test3() throws Exception {
        Date startDate = new Date(Instant.now().minus(Duration.ofDays(1)).toEpochMilli());
        Date endDate = new Date(Instant.now().plus(Duration.ofDays(1)).toEpochMilli());
        SalesDetailTable salesDetailTable = new SalesDetailTable(startDate, endDate);
        System.out.println(salesDetailTable.getSalesDetailTable());
    }

    @After
    public void tearDown() throws Exception {
        DocumentFilter.Builder builder = new DocumentFilter.Builder();
        builder.customer("2").creatorID("161250068").docState(DocState.UN_CHECKED)
                .docType(DocType.SALES).docType(DocType.GIFT);

        List<HistoryDocVO> voList = manager.findDocuments(builder.build());
    }
}