package nju.lighting.bl.customerbl;

import nju.lighting.bl.logbl.Logger;
import nju.lighting.bl.logbl.UserLogger;
import nju.lighting.dataservice.DataFactory;
import nju.lighting.dataservice.customerdataservice.CustomerDataService;
import nju.lighting.po.customer.CustomerPO;
import shared.ResultMessage;

import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created on 2017/12/5.
 * Description:
 * @author Liao
 */
public class CustomerInfoImpl implements CustomerInfo {
    private CustomerDataService dataService;

    public CustomerInfoImpl() {
        try {
            dataService = DataFactory.getDataBase(CustomerDataService.class);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ResultMessage changeReceivable(int customerId, double amount) {
        try {
            return dataService.changeReceivable(customerId, amount);
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.FAILURE;
        }
    }

    @Override
    public ResultMessage changePayable(int customerId, double amount) {
        try {
            return dataService.changePayable(customerId, amount);
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.FAILURE;
        }
    }

    @Override
    public List<Customer> getCustomerList() {
        try {
            List<CustomerPO> poList = dataService.getAllCustomer();
            return poList.stream().map(Customer::new).collect(Collectors.toList());
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }
}
