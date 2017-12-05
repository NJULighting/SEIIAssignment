package nju.lighting.dataservice.logdataservice;

import nju.lighting.po.log.LogPO;
import shared.Identity;
import shared.LogFilter;
import shared.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created on 2017/10/22.
 * Description:
 * @author Liao
 */
public interface LogDataService extends Remote {

    ResultMessage insert(LogPO po) throws RemoteException;

    List<LogPO> findByTime(Date from, Date to) throws RemoteException;

    List<LogPO> findById(String id) throws RemoteException;

    List<LogPO> findByIdentity(Identity identity) throws RemoteException;

    List<LogPO> findByTimeAndId(Date from, Date to, String id) throws RemoteException;

    List<LogPO> findByTimeAndIdentity(Date from, Date to, Identity identity) throws RemoteException;

}
