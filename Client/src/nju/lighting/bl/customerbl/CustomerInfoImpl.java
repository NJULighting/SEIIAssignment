package nju.lighting.bl.customerbl;

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
            double receivable = dataService.getCustomerById(customerId).getReceivable();
            if (receivable + amount < 0) {
                dataService.changeReceivable(customerId, -receivable);
                return dataService.changePayable(customerId, -receivable - amount);
            }
            return dataService.changeReceivable(customerId, amount);
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.FAILURE;
        }
    }

    @Override
    public ResultMessage changePayable(int customerId, double amount) {
        try {
            double payable = dataService.getCustomerById(customerId).getPayable();
            if (payable + amount < 0) {
                dataService.changePayable(customerId, -payable);
                return dataService.changeReceivable(customerId, -amount - payable);
            }
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
