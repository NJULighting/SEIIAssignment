package nju.lighting.blservice.logblservice;

import nju.lighting.vo.LogVO;
import shared.LogFilter;

import java.util.Date;
import java.util.List;

/**
 * Created on 2017/10/21.
 * Description:
 * @author Liao
 */
public interface LogBLService {
    List<LogVO> getLogListByTime(Date from, Date to);

    List<LogVO> findLogs(LogFilter filter);
}
