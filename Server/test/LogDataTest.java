
import nju.lighting.data.logdata.LogData;
import nju.lighting.po.log.LogPO;
import org.junit.Test;
import shared.Identity;
import shared.ResultMessage;

import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created on 2017/11/30.
 * Description:
 *
 * @author iznauy
 */
public class LogDataTest {

    private LogData logData;

    public LogDataTest() throws RemoteException {
        this.logData = new LogData();
    }

    @Test
    public void insert() throws Exception {
        LogPO logPO1 = new LogPO(new Date(), "161250220", "领导带头膜蛤！");
        LogPO logPO2 = new LogPO(new Date(), "161250220", "喂？110嘛？有人侮辱江主席，快控制不住场面了！");
        ResultMessage resultMessage1 = logData.insert(logPO1);
        ResultMessage resultMessage2 = logData.insert(logPO2);
        assertEquals(ResultMessage.SUCCESS, resultMessage1);
        assertEquals(ResultMessage.SUCCESS, resultMessage2);
    }

    @Test
    public void temp() throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date date = calendar.getTime();
        LogPO logPO1 = new LogPO(date, "161250068", "Naive!");
        LogPO logPO2 = new LogPO(date, "161250068", "Too Young, Too sample!");
        ResultMessage resultMessage1 = logData.insert(logPO1);
        ResultMessage resultMessage2 = logData.insert(logPO2);
        assertEquals(ResultMessage.SUCCESS, resultMessage1);
        assertEquals(ResultMessage.SUCCESS, resultMessage2);
    }

    @Test
    public void temp2() throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.MONTH, 11);
        Date date = calendar.getTime();
        LogPO logPO1 = new LogPO(date, "161250068", "Naive!");
        LogPO logPO2 = new LogPO(date, "161250068", "Too Young, Too sample!");
        ResultMessage resultMessage1 = logData.insert(logPO1);
        ResultMessage resultMessage2 = logData.insert(logPO2);
        assertEquals(ResultMessage.SUCCESS, resultMessage1);
        assertEquals(ResultMessage.SUCCESS, resultMessage2);
    }

    @Test
    public void findByTime() throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 9);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date begin = calendar.getTime();
        calendar.set(Calendar.MONTH, 10);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date end = calendar.getTime();
        System.out.println(begin);
        System.out.println(end);
        List<LogPO> logPOS;
        logPOS = logData.findByTime(begin, end);
        for (LogPO logPO: logPOS) {
            System.out.println(logPO);
        }
    }

    @Test
    public void findById() throws Exception {
        List<LogPO> logPOS;
        logPOS = logData.findById("161250068");
        for (LogPO logPO: logPOS) {
            System.out.println(logPO);
        }
    }

    @Test
    public void findByIdentity() throws Exception {
        List<LogPO> logPOS;
        logPOS = logData.findByIdentity(Identity.FINANCE);
        for (LogPO logPO: logPOS) {
            System.out.println(logPO);
        }
    }

    @Test
    public void findByTimeAndId() throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 9);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date begin = calendar.getTime();
        calendar.set(Calendar.MONTH, 10);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date end = calendar.getTime();
        System.out.println(begin);
        System.out.println(end);
        List<LogPO> logPOS;
        logPOS = logData.findByTimeAndId(begin, end, "161250068");
        for (LogPO logPO: logPOS) {
            System.out.println(logPO);
        }
    }

    @Test
    public void findByTimeAndIdentity() throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 9);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date begin = calendar.getTime();
        calendar.set(Calendar.MONTH, 10);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date end = calendar.getTime();
        System.out.println(begin);
        System.out.println(end);
        List<LogPO> logPOS;
        logPOS = logData.findByTimeAndIdentity(begin, end, Identity.GENERAL);
        for (LogPO logPO: logPOS) {
            System.out.println(logPO);
        }
    }

}