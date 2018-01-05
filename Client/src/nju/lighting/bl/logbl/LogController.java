package nju.lighting.bl.logbl;

import nju.lighting.blservice.logblservice.LogBLService;
import nju.lighting.vo.LogVO;
import shared.LogFilter;

import java.util.Date;
import java.util.List;

/**
 * Created on 2017/11/6.
 * Description:
 * @author Liao
 */
public class LogController implements LogBLService {
    private LogManager manager = LogManager.INSTANCE;

    @Override
    public List<LogVO> getLogListByTime(Date from, Date to) {
        return manager.getLogListByTime(from, to);
    }

    @Override
    public List<LogVO> findLogs(LogFilter filter) {
        return manager.findLogs(filter);
    }
}