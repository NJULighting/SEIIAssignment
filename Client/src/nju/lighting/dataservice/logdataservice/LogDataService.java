package nju.lighting.dataservice.logdataservice;

import nju.lighting.po.LogPO;
import shared.LogFilter;
import shared.ResultMessage;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created on 2017/10/22.
 * Description:
 * @author Liao
 */
public interface LogDataService {
    ResultMessage insert(LogPO po) throws RemoteException;

    ArrayList<LogPO> findByTime(Date from, Date to) throws RemoteException;

    LogPO find(LogFilter filter) throws RemoteException;

    void init() throws RemoteException;

    void finish() throws RemoteException;
}
