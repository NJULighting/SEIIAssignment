package nju.lighting.presentation.factory;

import nju.lighting.bl.repositorybl.RepositoryController;
import nju.lighting.blservice.repositoryblservice.RepositoryBLService;

/**
 * Created on 2017/12/30.
 * Description
 * @author 陈俊宇
 */
public class RepositoryBLServiceFactory {
    private static RepositoryBLService repositoryBLService = new RepositoryController();

    public static RepositoryBLService getRepositoryBLService() {
        return repositoryBLService;
    }
}
