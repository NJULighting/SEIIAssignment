package nju.lighting.presentation.factory;

import nju.lighting.bl.initbl.InitializationBLService_Stub;
import nju.lighting.blservice.initblservice.InitializationBLService;

/**
 * Created on 2017/12/30.
 * Description
 *
 * @author 陈俊宇
 */
public class InitializationBLServiceFactory {
    private InitializationBLService initializationBLService=new InitializationBLService_Stub();

    public InitializationBLService getInitializationBLService() {
        return initializationBLService;
    }
}