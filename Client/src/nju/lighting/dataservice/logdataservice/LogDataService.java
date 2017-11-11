package nju.lighting.dataservice.logdataservice;

import nju.lighting.po.LogPO;
import shared.LogFilter;
import shared.ResultMessage;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created on 2017/10/22.
 * Description:
 * @author Liao
 */
public interface LogDataService {
    ResultMessage insert(LogPO po);

    ArrayList<LogPO> findByTime(Date from, Date to);

    LogPO find(LogFilter filter);

    void init();

    void finish();
}
