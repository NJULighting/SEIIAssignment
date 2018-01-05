package nju.lighting.bl.logbl;

import nju.lighting.bl.userbl.UserInfo;
import nju.lighting.bl.userbl.UserInfoImpl;
import nju.lighting.bl.utils.DataServiceBiFunction;
import nju.lighting.dataservice.DataFactory;
import nju.lighting.dataservice.logdataservice.LogDataService;
import nju.lighting.po.log.LogPO;
import nju.lighting.vo.LogVO;
import shared.LogFilter;

import javax.naming.NamingException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * Created on 2018/1/5.
 * Description:
 * @author Liao
 */
public enum LogManager {
    INSTANCE;

    private LogDataService dataService;
    private UserInfo userInfo = new UserInfoImpl();
    private Function<LogPO, LogVO> poTransformer = po -> new LogVO(po.getTime(), po.getContent(),
            po.getUserID(), userInfo.getIdentityById(po.getUserID()));

    LogManager() {
        try {
            dataService = DataFactory.getDataBase(LogDataService.class);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    List<LogVO> getLogListByTime(Date from, Date to) {
        to = Optional.ofNullable(to).orElse(new Date());
        return DataServiceBiFunction.findToList(from, to, dataService::findByTime, poTransformer);
    }

    List<LogVO> findLogs(LogFilter filter) {
        return DataServiceBiFunction.findAndFilterToList(filter.getFrom(), filter.getTo(), dataService::findByTime,
                poTransformer, filter.getFilter());
    }
}
