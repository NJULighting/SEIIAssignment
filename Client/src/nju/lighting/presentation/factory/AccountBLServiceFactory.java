package nju.lighting.presentation.factory;

import nju.lighting.bl.accountbl.AccountBLService_Stub;
import nju.lighting.blservice.accountblservice.AccountBLService;

/**
 * Created on 2017/12/30.
 * Description
 * @author 陈俊宇
 */
public class AccountBLServiceFactory {
    private static AccountBLService accountBLService = new AccountBLService_Stub();

    public static AccountBLService getAccountBLService() {
        return accountBLService;
    }
}
