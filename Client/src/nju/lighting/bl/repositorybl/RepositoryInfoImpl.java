package nju.lighting.bl.repositorybl;

import nju.lighting.bl.commoditybl.CommodityInfo;
import nju.lighting.bl.commoditybl.CommodityInfoImpl;
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
            // Change the number of corresponding commodity
            if (!changeCommodityNumber(commodityID, count, type).success())
                return ResultMessage.FAILURE;

            // Add repository change information to database
            ResultMessage res = dataService.changeRepository(change.toPOForCreation());
            if (res.success()) {
                logger.add(OPType.ADD, "商品" + commodityID + "库存被修改，详情");
            }
            return res;
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.NETWORK_FAIL;
        }
    }

    private ResultMessage changeCommodityNumber(String commodityID, int count, RepositoryChangeType type) {
        CommodityInfo commodityInfo = new CommodityInfoImpl();
        if (type == RepositoryChangeType.GIFT || type == RepositoryChangeType.SELL
                || type == RepositoryChangeType.LOSS || type == RepositoryChangeType.RETURN) {
            return commodityInfo.addCommodityNumber(commodityID, -count);
        } else
            return commodityInfo.addCommodityNumber(commodityID, count);
    }
}
