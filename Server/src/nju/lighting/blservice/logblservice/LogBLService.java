package nju.lighting.blservice.logblservice;

import nju.lighting.po.ResultMessage;
import nju.lighting.vo.LogVO;

import java.util.ArrayList;

/**
 * Created on 2017/10/21.
 * Description:
 * @author Liao
 */
public interface LogBLService {
    ArrayList<LogVO> getLogListByTime(long from, long to);

    LogVO getLog(String id);

    ResultMessage addLog(LogVO vo);
}
