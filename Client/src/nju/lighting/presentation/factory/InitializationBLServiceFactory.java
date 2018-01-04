package nju.lighting.presentation.factory;

import nju.lighting.bl.initbl.InitController;
import nju.lighting.bl.initbl.InitializationBLService_Stub;
import nju.lighting.blservice.initblservice.InitializationBLService;

/**
 * Created on 2017/12/30.
 * Description
 *
 * @author 陈俊宇
 */
public class InitializationBLServiceFactory {
    private static InitializationBLService initializationBLService=new InitController();

    public static InitializationBLService getInitializationBLService() {
        return initializationBLService;
    }
}
