package nju.lighting.data.logdata;

import nju.lighting.dataservice.logdataservice.LogDataService;
import nju.lighting.po.LogPO;
import shared.LogFilter;
import shared.ResultMessage;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created on 2017/11/26.
 * Description:
 *
 * @author iznauy
 */
public class LogData implements LogDataService {

    @Override
    public ResultMessage insert(LogPO po) throws RemoteException {
        return null;
    }

    @Override
    public ArrayList<LogPO> findByTime(Date from, Date to) throws RemoteException {
        return null;
    }

    @Override
    public LogPO find(LogFilter filter) throws RemoteException {
        return null;
    }

}
