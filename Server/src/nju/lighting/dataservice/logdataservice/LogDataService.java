package nju.lighting.dataservice.logdataservice;

import nju.lighting.po.LogPO;
import nju.lighting.po.ResultMessage;

import java.util.ArrayList;

/**
 * Created on 2017/10/22.
 * Description:
 * @author Liao
 */
public interface LogDataService {
    ResultMessage insert(LogPO po);

    ArrayList<LogPO> findByTime(long from, long to);

    LogPO find(String ID);

    void init();

    void finish();
}
