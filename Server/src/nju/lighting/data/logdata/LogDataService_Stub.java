package nju.lighting.data.logdata;

import nju.lighting.blservice.logblservice.LogFilter;
import nju.lighting.dataservice.logdataservice.LogDataService;
import nju.lighting.po.LogPO;
import nju.lighting.po.ResultMessage;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created on 2017/10/22.
 * Description:
 * @author Liao
 */
public class LogDataService_Stub implements LogDataService {
    @Override
    public ResultMessage insert(LogPO po) {
        return po == null ? ResultMessage.FAILURE : ResultMessage.SUCCESS;
    }

    @Override
    public ArrayList<LogPO> findByTime(Date from, Date to) {
        LogPO log0 = new LogPO(new Date(), "001", "Excited", "12345");
        LogPO log1 = new LogPO(new Date(), "001", "Too Simple","12345");
        ArrayList<LogPO> logs = new ArrayList<>();
        logs.add(log0);
        logs.add(log1);
        return logs;
    }

    @Override
    public LogPO find(LogFilter filter) {
        LogPO log = new LogPO(new Date(), "001", "Excited", "12345");
        return filter == null ? null : log;
    }

    @Override
    public void init() {
        System.out.println("init");
    }

    @Override
    public void finish() {
        System.out.println("finish");
    }
}
