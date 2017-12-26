package nju.lighting.bl.documentbl;

import nju.lighting.bl.userbl.LoginTestHelper;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.viewtables.BusinessHistoryItemVO;
import org.junit.Before;
import org.junit.Test;
import shared.DocumentFilter;
import shared.ResultMessage;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created on 2017/12/26.
 * Description:
 * @author Liao
 */
public class RedFlushTest {
    private DocManager manager = DocManager.INSTANCE;

    @Before
    public void setUp() throws Exception {
        LoginTestHelper.loginAuthorizedUser();
    }

    @Test
    public void redFlush() throws Exception {
        DocumentFilter.Builder builder = new DocumentFilter.Builder();
        builder.startDate(new Date(Instant.now().minus(Duration.ofDays(30)).toEpochMilli()));
        List<BusinessHistoryItemVO> voList = manager.findBusinessHistory(builder.build());

        System.out.println(voList.get(0));

        RedFlush redFlush = new RedFlush();
        DocVO copy = redFlush.redFlushAndCopy(voList.get(1).getDocVO());
        ResultMessage res = redFlush.redFlush(voList.get(0).getDocVO());
        assertEquals(ResultMessage.SUCCESS, res);
    }

}