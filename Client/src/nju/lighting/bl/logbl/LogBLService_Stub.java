package nju.lighting.bl.logbl;

import nju.lighting.blservice.logblservice.LogBLService;
import nju.lighting.presentation.utils.DateHelper;
import nju.lighting.vo.LogVO;
import shared.Identity;
import shared.LogFilter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created on 2017/10/22.
 * Description:
 * @author Liao
 */
public class LogBLService_Stub implements LogBLService {

    @Override
    public List<LogVO> getLogListByTime(Date from, Date to) {
        Date aWeekAgo = DateHelper.weekAgo();
        LogVO logVO0 = new LogVO(aWeekAgo, "Too young", "Frog0", Identity.FINANCE);
        LogVO logVO1 = new LogVO(new Date(), "Too simple", "Frog1", Identity.GENERAL);


        ArrayList<LogVO> logs = new ArrayList<>();
        logs.add(logVO0);
        logs.add(logVO0);
        logs.add(logVO0);
        logs.add(logVO0);
        logs.add(logVO0);
        logs.add(logVO0);
        logs.add(logVO0);
        logs.add(logVO0);
        logs.add(logVO0);
        logs.add(logVO0);
        logs.add(logVO0);
        logs.add(logVO0);
        logs.add(logVO0);
        logs.add(logVO0);
        logs.add(logVO0);
        logs.add(logVO0);
        logs.add(logVO0);
        logs.add(logVO0);
        logs.add(logVO0);
        logs.add(logVO1);
        logs.add(logVO1);
        logs.add(logVO1);
        logs.add(logVO1);
        logs.add(logVO1);
        logs.add(logVO1);
        logs.add(logVO1);
        logs.add(logVO1);
        logs.add(logVO1);
        logs.add(logVO1);
        logs.add(logVO1);
        logs.add(logVO1);
        logs.add(logVO1);
        logs.add(logVO1);
        logs.add(logVO1);
        logs.add(logVO1);
        logs.add(logVO1);
        logs.add(logVO1);
        logs.add(logVO1);
        logs.add(logVO1);
        logs.add(logVO1);
        logs.add(logVO1);


        return logs;
    }


    @Override
    public List<LogVO> findLogs(LogFilter filter) {
        Date date1 = new Date();
        Date date2 = new Date(System.currentTimeMillis());
        return getLogListByTime(date1, date2);
    }
}
