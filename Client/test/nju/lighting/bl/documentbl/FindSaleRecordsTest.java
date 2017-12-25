package nju.lighting.bl.documentbl;

import nju.lighting.bl.userbl.LoginTestHelper;
import nju.lighting.vo.viewtables.BusinessConditionItemVO;
import org.junit.Before;
import org.junit.Test;
import shared.DocumentFilter;

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
}