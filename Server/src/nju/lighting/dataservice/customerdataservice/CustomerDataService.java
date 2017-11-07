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

}
