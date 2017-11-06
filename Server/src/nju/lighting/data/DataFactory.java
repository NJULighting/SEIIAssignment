package nju.lighting.data;

import nju.lighting.dataservice.commoditydataservice.CommodityDataService;
import nju.lighting.dataservice.repositorydataservice.RepositoryDataService;

public abstract class DataFactory {


    public static RepositoryDataService getRepositoryDataService() {
        return null;
    }

    public static CommodityDataService getCommodityDataService() {
        return null;
    }
    

}
