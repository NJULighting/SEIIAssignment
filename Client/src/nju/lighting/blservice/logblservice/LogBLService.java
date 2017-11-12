package nju.lighting.blservice.logblservice;

import nju.lighting.vo.LogVO;
import shared.LogFilter;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created on 2017/10/21.
 * Description:
 * @author Liao
 */
public interface LogBLService {
    ArrayList<LogVO> getLogListByTime(Date from, Date to) ;

    ArrayList<LogVO> findLogs(LogFilter filter) ;
}
