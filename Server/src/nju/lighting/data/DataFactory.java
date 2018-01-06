package nju.lighting.data;

import nju.lighting.data.commoditydata.CommodityDataService_Stub;
import nju.lighting.data.customerdata.CustomerDataService_Stub;
import nju.lighting.data.logdata.LogDataService_Stub;
import nju.lighting.data.promotiondata.PromotionDataService_Stub;
import nju.lighting.data.repositorydata.RepositoryDataService_Stub;
import nju.lighting.dataservice.accountdataservice.AccountDataService;
import nju.lighting.dataservice.commoditydataservice.CommodityDataService;
import nju.lighting.dataservice.customerdataservice.CustomerDataService;
import nju.lighting.dataservice.logdataservice.LogDataService;
import nju.lighting.dataservice.promotiondataservice.PromotionDataService;
import nju.lighting.dataservice.repositorydataservice.RepositoryDataService;

public abstract class DataFactory {


    public static RepositoryDataService getRepositoryDataService () {
        return new RepositoryDataService_Stub();
    }

    public static CommodityDataService getCommodityDataService() {
        return new CommodityDataService_Stub();
    }

    public static AccountDataService getAccountDataService () {
        //return new AccountDataService_Stub();
        return null;
    }
    public static CustomerDataService getCustomerDataService() {
        return new CustomerDataService_Stub();
    }

    public static LogDataService getLogDataService() {
        return new LogDataService_Stub();
    }

    public static PromotionDataService getPromotionDataService() {
        return new PromotionDataService_Stub();
    }



}
