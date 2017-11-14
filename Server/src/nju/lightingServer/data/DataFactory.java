package nju.lightingServer.data;

import nju.lightingServer.data.approvaldata.ApprovalDataService_Stub;
import nju.lightingServer.data.commoditydata.CommodityDataService_Stub;
import nju.lightingServer.data.customerdata.CustomerDataService_Stub;
import nju.lightingServer.data.documentdata.DocDataService_Stub;
import nju.lightingServer.data.logdata.LogDataService_Stub;
import nju.lightingServer.data.promotiondata.PromotionDataService_Stub;
import nju.lightingServer.data.repositorydata.RepositoryDataService_Stub;
import nju.lightingServer.data.userdata.UserDataService_Stub;
import nju.lighting.dataservice.accountdataservice.AccountDataService;
import nju.lighting.dataservice.approvaldataservice.ApprovalDataService;
import nju.lighting.dataservice.commoditydataservice.CommodityDataService;
import nju.lighting.dataservice.customerdataservice.CustomerDataService;
import nju.lighting.dataservice.documentdataservice.DocDataService;
import nju.lighting.dataservice.logdataservice.LogDataService;
import nju.lighting.dataservice.promotiondataservice.PromotionDataService;
import nju.lighting.dataservice.repositorydataservice.RepositoryDataService;
import nju.lighting.dataservice.userdataservice.UserDataService;

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

    public static ApprovalDataService getApprovalDataService() {
        return new ApprovalDataService_Stub();
    }

    public static CustomerDataService getCustomerDataService() {
        return new CustomerDataService_Stub();
    }

    public static DocDataService getDocDataService() {
        return new DocDataService_Stub();
    }

    public static LogDataService getLogDataService() {
        return new LogDataService_Stub();
    }

    public static PromotionDataService getPromotionDataService() {
        return new PromotionDataService_Stub();
    }

    public static UserDataService getUserDataService() {
        return new UserDataService_Stub();
    }

}
