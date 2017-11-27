package nju.lighting.data.customerdata;

import nju.lighting.data.utils.CommonOperation;
import nju.lighting.dataservice.customerdataservice.CustomerDataService;
import nju.lighting.po.customer.CustomerPO;
import shared.ResultMessage;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Created on 2017/11/26.
 * Description: 客户模块数据层
 *
 * @author iznauy
 */
public class CustomerData implements CustomerDataService {

    private CommonOperation<CustomerPO> commonOperation = null;

    public CustomerData() {
        this.commonOperation = new CommonOperation<>(CustomerPO.class.getName());
    }

    @Override
    public List<CustomerPO> getAllCustomer() throws RemoteException {
        return commonOperation.getAll();
    }

    @Override
    public ResultMessage insertCustomer(CustomerPO po) throws RemoteException {
        return commonOperation.add(po);
    }

    @Override
    public ResultMessage deleteCustomer(String id) throws RemoteException {
        return commonOperation.deleteBySingleField("id", id);
    }

    @Override
    public ResultMessage updateCustomer(CustomerPO po) throws RemoteException {
        return commonOperation.update(po);
    }

    @Override
    public CustomerPO getCustomerById(String id) throws RemoteException {
        return commonOperation.getBySingleField("id", id);
    }

    @Override
    public ResultMessage changeReceivable(String customerId, double amount) throws RemoteException {
        CustomerPO customerPO = getCustomerById(customerId);
        customerPO.setReceivable(amount);
        return updateCustomer(customerPO);
    }

    @Override
    public ResultMessage changeReceivableLimit(String customerId, double amount) throws RemoteException {
        CustomerPO customerPO = getCustomerById(customerId);
        customerPO.setReceivableLimit(amount);
        return updateCustomer(customerPO);
    }

    @Override
    public ResultMessage changePayable(String customerId, double amount) throws RemoteException {
        CustomerPO customerPO = getCustomerById(customerId);
        customerPO.setPayable(amount);
        return updateCustomer(customerPO);
    }
}
