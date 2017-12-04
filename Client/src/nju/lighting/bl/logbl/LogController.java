package nju.lighting.bl.logbl;

import nju.lighting.blservice.logblservice.LogBLService;
import nju.lighting.vo.LogVO;
import shared.LogFilter;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created on 2017/11/6.
 * Description:
 * @author Liao
 */
public class LogController implements LogBLService {
    @Override
    public ArrayList<LogVO> getLogListByTime(Date from, Date to) {
        return null;
    }

    @Override
    public ArrayList<LogVO> findLogs(LogFilter filter) {
        return null;
    }
}
