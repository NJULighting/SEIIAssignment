package nju.lighting.data.logdata;

import shared.Identity;
import shared.LogFilter;
import nju.lighting.dataservice.logdataservice.LogDataService;
import nju.lighting.po.log.LogPO;
import shared.ResultMessage;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created on 2017/10/22.
 * Description:
 * @author Liao
 */
public class LogDataService_Stub implements LogDataService {

    @Override
    public ResultMessage insert(LogPO po) throws RemoteException {
        return null;
    }

    @Override
    public List<LogPO> findByTime(Date from, Date to) throws RemoteException {
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
