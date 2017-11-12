package nju.lighting.dataservice.customerdataservice;

import nju.lighting.po.customer.CustomerPO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface CustomerDataService {

     ArrayList<CustomerPO> getAllCustomer() throws RemoteException;

     CustomerPO findCustomer(String keyword) throws RemoteException;

     void insertCustomer(CustomerPO po) throws RemoteException;

     void deleteCustomer(CustomerPO po) throws RemoteException;

     void updateCustomer(CustomerPO po) throws RemoteException;

     int getNextCustomerID() throws RemoteException;

     /**
      * 若增加客户应收，amount为正数，反之为负
      * @param amount
      */
     void changeReceivable(double amount) throws RemoteException;

     /**
      * 若增加客户应付，amount为正数，反之为负
      * @param amount
      */
     void changePayable(double amount) throws RemoteException;

}
