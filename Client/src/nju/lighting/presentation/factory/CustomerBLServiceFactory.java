package nju.lighting.presentation.factory;

import nju.lighting.bl.customerbl.CustomerBLService_Stub;
import nju.lighting.bl.customerbl.CustomerController;
import nju.lighting.blservice.customerblservice.CustomerBLService;

/**
 * Created on 2017/12/30.
 * Description
 * @author 陈俊宇
 */
public class CustomerBLServiceFactory {
    private static CustomerBLService customerBLService = new CustomerController();

    public static CustomerBLService getCustomerBLService() {
        return customerBLService;
    }
}
