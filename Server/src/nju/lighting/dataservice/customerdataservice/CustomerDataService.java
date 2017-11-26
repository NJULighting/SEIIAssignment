package nju.lighting.dataservice.customerdataservice;

import nju.lighting.po.customer.CustomerPO;
import shared.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface CustomerDataService extends Remote {

     List<CustomerPO> getAllCustomer() throws RemoteException;

     ResultMessage insertCustomer(CustomerPO po) throws RemoteException;

     ResultMessage deleteCustomer(String id) throws RemoteException;

     ResultMessage updateCustomer(CustomerPO po) throws RemoteException;

     CustomerPO getCustomerById(String id) throws RemoteException;

     /**
      * 若增加客户应收，amount为正数，反之为负
      * @param amount
      */
     ResultMessage changeReceivable(String customerId, double amount) throws RemoteException;

     ResultMessage changeReceivableLimit(String customerId, double amount) throws RemoteException;

     /**
      * 若增加客户应付，amount为正数，反之为负
      * @param amount
      */
     ResultMessage changePayable(String customerId, double amount) throws RemoteException;

}
