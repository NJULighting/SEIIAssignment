package nju.lighting.data.customerdata;

import nju.lighting.data.utils.CommonOperation;
import nju.lighting.dataservice.customerdataservice.CustomerDataService;
import nju.lighting.po.customer.CustomerPO;
import shared.CustomerGrade;
import shared.Result;
import shared.ResultMessage;
import shared.TwoTuple;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collections;
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
    public Result<Integer> insertCustomer(CustomerPO po) throws RemoteException {
        ResultMessage res = commonOperation.add(po);
        return new Result<>(res, po.getID());
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
        customerPO.setReceivable(customerPO.getReceivable() + amount);
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
        customerPO.setPayable(customerPO.getPayable() + amount);
        return updateCustomer(customerPO);
    }

    @Override
    public List<CustomerPO> fuzzySearchByName(String key) throws RemoteException {
        return commonOperation.fuzzySearch("name", key);
    }

    @Override
    public List<CustomerPO> fuzzySearchByGrade(String key) throws RemoteException {
        CustomerGrade grade = CustomerGrade.get(key);
        if (grade == null)
            return Collections.emptyList();
        return commonOperation.fuzzySearch("grade", grade);
    }

    @Override
    public List<CustomerPO> fuzzySearchByTelephone(String key) throws RemoteException {
        return commonOperation.fuzzySearch("telephone", key);
    }

    @Override
    public List<CustomerPO> fuzzySearchByEmail(String key) throws RemoteException {
        return commonOperation.fuzzySearch("email", key);
    }

    @Override
    public List<CustomerPO> fuzzySearchByAddress(String key) throws RemoteException {
        return commonOperation.fuzzySearch("address", key);
    }

    @Override
    public List<CustomerPO> fuzzySearchById(Integer id) throws RemoteException {
        return commonOperation.fuzzySearch("id", id);
    }
}
