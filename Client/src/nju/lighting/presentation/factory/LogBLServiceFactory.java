package nju.lighting.presentation.factory;

import nju.lighting.bl.logbl.LogBLService_Stub;
import nju.lighting.blservice.logblservice.LogBLService;

/**
 * Created on 2017/12/30.
 * Description
 *
 * @author 陈俊宇
 */
public class LogBLServiceFactory {
    private static LogBLService logBLService=new LogBLService_Stub();

    public static LogBLService getLogBLService() {
        return logBLService;
    }
}
