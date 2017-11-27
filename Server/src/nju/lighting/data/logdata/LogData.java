package nju.lighting.data.logdata;

import nju.lighting.dataservice.logdataservice.LogDataService;
import nju.lighting.po.LogPO;
import shared.Identity;
import shared.LogFilter;
import shared.ResultMessage;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    public List<LogPO> findByTime(Date from, Date to) throws RemoteException {
        return null;
    }

    @Override
    public LogPO find(LogFilter filter) throws RemoteException {
        return null;
    }

    @Override
    public List<LogPO> findById(String id) throws RemoteException {
        return null;
    }

    @Override
    public List<LogPO> findByIdentity(Identity identity) throws RemoteException {
        return null;
    }

    @Override
    public List<LogPO> findByTimeAndId(Date from, Date to, String id) throws RemoteException {
        return null;
    }

    @Override
    public List<LogPO> findByTimeAndIdentity(Date from, Date to, Identity identity) throws RemoteException {
        return null;
    }
}
