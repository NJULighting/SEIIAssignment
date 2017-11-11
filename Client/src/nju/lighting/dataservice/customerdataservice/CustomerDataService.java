package nju.lighting.dataservice.customerdataservice;

import nju.lighting.po.customer.CustomerPO;

import java.util.ArrayList;

public interface CustomerDataService {

     ArrayList<CustomerPO> getAllCustomer();

     CustomerPO findCustomer(String keyword);

     void insertCustomer(CustomerPO po);

     void deleteCustomer(CustomerPO po);

     void updateCustomer(CustomerPO po);

     int getNextCustomerID();

     /**
      * 若增加客户应收，amount为正数，反之为负
      * @param amount
      */
     void changeReceivable(double amount);

     /**
      * 若增加客户应付，amount为正数，反之为负
      * @param amount
      */
     void changePayable(double amount);

}
