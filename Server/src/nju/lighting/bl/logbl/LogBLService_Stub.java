package nju.lighting.bl.logbl;

import nju.lighting.blservice.logblservice.LogBLService;
import nju.lighting.blservice.logblservice.LogFilter;
import nju.lighting.vo.LogVO;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created on 2017/10/22.
 * Description:
 * @author Liao
 */
public class LogBLService_Stub implements LogBLService {

    @Override
    public ArrayList<LogVO> getLogListByTime(Date from, Date to) throws RemoteException {
        LogVO logVO0 = new LogVO(1234567, "Too young");
        LogVO logVO1 = new LogVO(1234568, "Too simple");
        ArrayList<LogVO> logs = new ArrayList<>();
        logs.add(logVO0);
        logs.add(logVO1);
        return logs;
    }


    @Override
    public ArrayList<LogVO> findLogs(LogFilter filter) throws RemoteException {
        Date date1 = new Date();
        Date date2 = new Date(System.currentTimeMillis());
        return getLogListByTime(date1, date2);
    }
}
