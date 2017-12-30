package nju.lighting.presentation.factory;

import nju.lighting.bl.userbl.UserBLServie_Stub;
import nju.lighting.blservice.userblservice.UserBLService;

/**
 * Created on 2017/12/30.
 * Description
 *
 * @author 陈俊宇
 */
public class UserBLServiceFactory {
    private static UserBLService userBLService=new UserBLServie_Stub();

    public static UserBLService getUserBLService() {
        return userBLService;
    }
}
