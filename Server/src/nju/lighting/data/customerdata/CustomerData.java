package nju.lighting.data.customerdata;

import nju.lighting.data.utils.CommonOperation;
import nju.lighting.dataservice.customerdataservice.CustomerDataService;
import nju.lighting.po.customer.CustomerPO;
import shared.ResultMessage;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 * Created on 2017/11/26.
 * Description: 客户模块数据层
 *
 * @author iznauy
 */
public class CustomerData extends UnicastRemoteObject implements CustomerDataService {

    private CommonOperation<CustomerPO> commonOperation = null;

    public CustomerData() throws RemoteException {
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
    public ResultMessage deleteCustomer(int id) throws RemoteException {
        return commonOperation.deleteBySingleField("id", id);
    }

    @Override
    public ResultMessage updateCustomer(CustomerPO po) throws RemoteException {
        return commonOperation.update(po);
    }

    @Override
    public CustomerPO getCustomerById(int id) throws RemoteException {
        return commonOperation.getBySingleField("id", id);
    }

    @Override
    public ResultMessage changeReceivable(int customerId, double amount) throws RemoteException {
        CustomerPO customerPO = getCustomerById(customerId);
        if (customerPO == null)
            return ResultMessage.FAILURE;
        customerPO.setReceivable(amount);
        return updateCustomer(customerPO);
    }

    @Override
    public ResultMessage changeReceivableLimit(int customerId, double amount) throws RemoteException {
        CustomerPO customerPO = getCustomerById(customerId);
        if (customerPO == null)
            return ResultMessage.FAILURE;
        customerPO.setReceivableLimit(amount);
        return updateCustomer(customerPO);
    }

    @Override
    public ResultMessage changePayable(int customerId, double amount) throws RemoteException {
        CustomerPO customerPO = getCustomerById(customerId);
        if (customerPO == null)
            return ResultMessage.FAILURE;
        customerPO.setPayable(amount);
        return updateCustomer(customerPO);
    }
}
