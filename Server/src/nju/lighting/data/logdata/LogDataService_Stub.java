package nju.lighting.data.logdata;

import nju.lighting.dataservice.logdataservice.LogDataService;
import nju.lighting.po.LogPO;
import nju.lighting.po.ResultMessage;

import java.util.ArrayList;

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
    public ArrayList<LogPO> findByTime(long from, long to) {
        LogPO log0 = new LogPO(123452, "001", "Excited");
        LogPO log1 = new LogPO(123456, "001", "Too Simple");
        ArrayList<LogPO> logs = new ArrayList<>();
        logs.add(log0);
        logs.add(log1);
        return logs;
    }

    @Override
    public LogPO find(String ID) {
        LogPO log = new LogPO(123452, "001", "Excited");
        return ID.isEmpty() ? null : log;
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
