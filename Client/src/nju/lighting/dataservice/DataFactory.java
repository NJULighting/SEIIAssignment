package nju.lighting.dataservice;

import nju.lighting.dataservice.accountdataservice.AccountDataService;
import nju.lighting.dataservice.commoditydataservice.CommodityDataService;
import nju.lighting.dataservice.customerdataservice.CustomerDataService;
import nju.lighting.dataservice.documentdataservice.DocDataService;
import nju.lighting.dataservice.initdataservice.InitDataService;
import nju.lighting.dataservice.logdataservice.LogDataService;
import nju.lighting.dataservice.promotiondataservice.PromotionDataService;
import nju.lighting.dataservice.repositorydataservice.RepositoryDataService;
import nju.lighting.dataservice.userdataservice.UserDataService;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created on 2017/11/12.
 * Description:
 * @author Liao
 */
public class DataFactory {

    private static Map<Class, String> databaseMap = new HashMap<>();

    static {
        databaseMap.put(UserDataService.class, "userDataService");
        databaseMap.put(AccountDataService.class, "accountDataService");
        databaseMap.put(LogDataService.class, "logDataService");
        databaseMap.put(CustomerDataService.class, "customerDataService");
        databaseMap.put(InitDataService.class, "initDataService");
        databaseMap.put(PromotionDataService.class, "promotionDataService");
        databaseMap.put(RepositoryDataService.class, "repositoryDataService");
        databaseMap.put(CommodityDataService.class, "commodityDataService");
        databaseMap.put(DocDataService.class, "docDataService");
    }

    public static AccountDataService getAccountDataBase() throws NamingException {
        Context namingContext = new InitialContext();
        return (AccountDataService) namingContext.lookup("rmi://localhost:8888/accountDataService");
    }

    public static <R> R getDataBase(Class<R> type) throws NamingException {
        Context namingContext = new InitialContext();
        return type.cast(namingContext.lookup("rmi://localhost:8888/" + databaseMap.get(type)));
    }
}
