package nju.lighting.blservice.logblservice;

import nju.lighting.vo.LogVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created on 2017/10/21.
 * Description:
 * @author Liao
 */
public interface LogBLService {
    ArrayList<LogVO> getLogListByTime(long from, long to) throws RemoteException;

    LogVO getLog(String id) throws RemoteException;

    ArrayList<LogVO> findLogs(LogFilter filter) throws RemoteException;
}
