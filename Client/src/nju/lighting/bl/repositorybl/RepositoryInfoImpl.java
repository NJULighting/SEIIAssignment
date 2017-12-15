package nju.lighting.bl.repositorybl;

import nju.lighting.bl.logbl.Logger;
import nju.lighting.bl.logbl.UserLogger;
import nju.lighting.dataservice.DataFactory;
import nju.lighting.dataservice.repositorydataservice.RepositoryDataService;
import shared.OPType;
import shared.RepositoryChangeType;
import shared.ResultMessage;

import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.Date;

/**
 * Created on 2017/12/13.
 * Description:
 * @author Liao
 */
public class RepositoryInfoImpl implements RepositoryInfo {

    private RepositoryDataService dataService;
    private Logger logger;

    public RepositoryInfoImpl() {
        try {
            dataService = DataFactory.getDataBase(RepositoryDataService.class);
            logger = new UserLogger();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ResultMessage changeRepository(String commodityID, int count, double amount, RepositoryChangeType type) {
        RepositoryChange change = new RepositoryChange(commodityID, type, count, amount, new Date());
        try {
            ResultMessage res = dataService.changeRepository(change.toPOForCreation());
            if (res == ResultMessage.SUCCESS)
                logger.add(OPType.ADD, "商品" + commodityID + "库存被修改，详情");
            return res;
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.NETWORK_FAIL;
        }
    }
}