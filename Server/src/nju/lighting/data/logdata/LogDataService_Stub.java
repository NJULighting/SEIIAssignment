package nju.lighting.data.logdata;

import nju.lighting.dataservice.logdataservice.LogDataService;
import nju.lighting.po.log.LogPO;
import shared.Identity;
import shared.Result;
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

@Deprecated
public class LogDataService_Stub implements LogDataService {

    @Override
    public ResultMessage insert(LogPO po) throws RemoteException {
        return po == null ? ResultMessage.SUCCESS : ResultMessage.FAILURE;
    }

    @Override
    public List<LogPO> findByTime(Date from, Date to) throws RemoteException {
        LogPO logPO = new LogPO(from, "161250220", "233");
        ArrayList<LogPO> logPOS = new ArrayList<>();
        logPOS.add(logPO);
        return logPOS;
    }

    @Override
    public List<LogPO> findById(String id) throws RemoteException {
        LogPO logPO = new LogPO(new Date(), id, "233");
        ArrayList<LogPO> logPOS = new ArrayList<>();
        logPOS.add(logPO);
        return logPOS;
    }

    @Override
    public List<LogPO> findByIdentity(Identity identity) throws RemoteException {
        LogPO logPO = new LogPO(new Date(), "161250220", "233");
        ArrayList<LogPO> logPOS = new ArrayList<>();
        logPOS.add(logPO);
        return logPOS;
    }

    @Override
    public List<LogPO> findByTimeAndId(Date from, Date to, String id) throws RemoteException {
        LogPO logPO = new LogPO(from, id, "233");
        ArrayList<LogPO> logPOS = new ArrayList<>();
        logPOS.add(logPO);
        return logPOS;
    }

    @Override
    public List<LogPO> findByTimeAndIdentity(Date from, Date to, Identity identity) throws RemoteException {
        LogPO logPO = new LogPO(from, "161250220", "233");
        ArrayList<LogPO> logPOS = new ArrayList<>();
        logPOS.add(logPO);
        return logPOS;
    }
}
