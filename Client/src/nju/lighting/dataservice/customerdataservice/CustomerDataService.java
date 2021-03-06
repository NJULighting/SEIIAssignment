package nju.lighting.dataservice.customerdataservice;

import nju.lighting.po.customer.CustomerPO;
import shared.Result;
import shared.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface CustomerDataService extends Remote {

    List<CustomerPO> getAllCustomer() throws RemoteException;

    Result<Integer> insertCustomer(CustomerPO po) throws RemoteException;

    ResultMessage deleteCustomer(int id) throws RemoteException;

    ResultMessage updateCustomer(CustomerPO po) throws RemoteException;

    CustomerPO getCustomerById(int id) throws RemoteException;

    List<CustomerPO> fuzzySearchByName(String key) throws RemoteException;

    List<CustomerPO> fuzzySearchById(Integer id) throws RemoteException;

    List<CustomerPO> fuzzySearchByTelephone(String key) throws RemoteException;

    List<CustomerPO> fuzzySearchByAddress(String key) throws RemoteException;

    List<CustomerPO> fuzzySearchByGrade(String key) throws RemoteException;

    List<CustomerPO> fuzzySearchByEmail(String key) throws RemoteException;

    /**
     * 若增加客户应收，amount为正数，反之为负
     * @param amount
     */
    ResultMessage changeReceivable(int customerId, double amount) throws RemoteException;

    ResultMessage changeReceivableLimit(int customerId, double amount) throws RemoteException;

    /**
     * 若增加客户应付，amount为正数，反之为负
     * @param amount
     */
    ResultMessage changePayable(int customerId, double amount) throws RemoteException;

}
