package nju.lighting.blservice.logblservice;

import nju.lighting.vo.LogVO;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created on 2017/10/21.
 * Description:
 * @author Liao
 */
public interface LogBLService {
    ArrayList<LogVO> getLogListByTime(Date from, Date to) throws RemoteException;

    ArrayList<LogVO> findLogs(LogFilter filter) throws RemoteException;
}
