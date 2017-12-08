package nju.lighting.data.logdata;

import nju.lighting.data.userdata.UserData;
import nju.lighting.data.userdata.UserService;
import nju.lighting.data.utils.CommonOperation;
import nju.lighting.dataservice.logdataservice.LogDataService;
import nju.lighting.po.log.LogPO;
import nju.lighting.po.user.UserPO;
import shared.Identity;
import shared.ResultMessage;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;

/**
 * Created on 2017/11/26.
 * Description:
 *
 * @author iznauy
 */
public class LogData extends UnicastRemoteObject implements LogDataService {

    private CommonOperation<LogPO> logPOCommonOperation;

    public LogData() throws RemoteException {
        logPOCommonOperation = new CommonOperation<>(LogPO.class.getName());
    }

    @Override
    public ResultMessage insert(LogPO po) throws RemoteException {
        return logPOCommonOperation.add(po);
    }

    @Override
    public List<LogPO> findByTime(Date from, Date to) throws RemoteException {
        return logPOCommonOperation.getDataBetweenTime(from, to, "time");
    }

    @Override
    public List<LogPO> findById(String id) throws RemoteException {
        return logPOCommonOperation.getListBySingleField("userID", id);
    }

    @Override
    public List<LogPO> findByIdentity(Identity identity) throws RemoteException {
        UserService userData = new UserData();
        List<UserPO> userPOS = userData.getByIdentity(identity);
        ArrayList<LogPO> logLists = new ArrayList<>();
        for (UserPO user: userPOS) {
            String id = user.getId();
            List<LogPO> pos = findById(id);
            logLists.addAll(pos);
        }
        return logLists;
    }

    @Override
    public List<LogPO> findByTimeAndId(Date from, Date to, String id) throws RemoteException {
        List<LogPO> originalLogs = findByTime(from, to);
        List<LogPO> filteredLog = new ArrayList<>();
        for (LogPO log: originalLogs) {
            if (log.getUserID().equals(id))
                filteredLog.add(log);
        }
        return filteredLog;
    }

    @Override
    public List<LogPO> findByTimeAndIdentity(Date from, Date to, Identity identity) throws RemoteException {
        List<LogPO> originalLogs = findByTime(from, to);
        UserService userData = new UserData();
        List<UserPO> userPOS = userData.getByIdentity(identity);
        TreeSet<String> userIds = new TreeSet<>();
        for (UserPO userPO: userPOS)
            userIds.add(userPO.getId());
        List<LogPO> filteredLog = new ArrayList<>();
        for (LogPO logPO: originalLogs) {
            String id = logPO.getUserID();
            if (userIds.contains(id))
                filteredLog.add(logPO);
        }
        return filteredLog;
    }
}
