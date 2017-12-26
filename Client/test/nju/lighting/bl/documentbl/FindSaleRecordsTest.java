package nju.lighting.bl.documentbl;

import nju.lighting.bl.userbl.LoginTestHelper;
import nju.lighting.blservice.documentblservice.DocBLService;
import nju.lighting.vo.viewtables.BusinessConditionItemVO;
import nju.lighting.vo.viewtables.BusinessHistoryItemVO;
import org.junit.Before;
import org.junit.Test;
import shared.DocType;
import shared.DocumentFilter;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

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
        builder.commodity("LED5W灯泡");
        builder.customer("1");

        List<BusinessConditionItemVO> itemList = manager.findSaleRecords(builder.build());

        assertEquals(1, itemList.size());
    }

    @Test
    public void test1() throws Exception {
        builder.creatorID(LoginTestHelper.AUTHORIZED_USER);

        assertEquals(2, manager.findSaleRecords(builder.build()).size());
    }

    @Test
    public void test2() throws Exception {
        builder.docType(DocType.ACCOUNT_IN);
        builder.docType(DocType.SALES);

        List<BusinessHistoryItemVO> itemList = manager.findBusinessHistory(builder.build());

        assertEquals(6, itemList.size());
    }

    @Test
    public void test3() throws Exception {
        Date startDate = new Date(Instant.now().minus(Duration.ofDays(1)).toEpochMilli());
        Date endDate = new Date(Instant.now().plus(Duration.ofDays(1)).toEpochMilli());
        SalesDetailTable salesDetailTable = new SalesDetailTable(startDate, endDate);
        System.out.println(salesDetailTable.getSalesDetailTable());
    }
}