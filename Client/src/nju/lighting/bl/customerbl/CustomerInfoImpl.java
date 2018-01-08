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
            CustomerPO customer = dataService.getCustomerById(customerId);
            double receivable = customer.getReceivable();

            // See whether the final amount will be negative or exceed the limit of receivable
            if (receivable + amount < 0) {
                dataService.changeReceivable(customerId, -receivable);
                return dataService.changePayable(customerId, -receivable - amount);
            } else if (receivable + amount > customer.getReceivableLimit()) {
                return ResultMessage.FAILURE;
            }

            return dataService.changeReceivable(customerId, amount);
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.NETWORK_FAIL;
        }
    }

    @Override
    public ResultMessage changePayable(int customerId, double amount) {
        try {
            CustomerPO customer = dataService.getCustomerById(customerId);
            double payable = customer.getPayable();

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
