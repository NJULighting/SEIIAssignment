package nju.lighting.data.customerdata;

import nju.lighting.dataservice.customerdataservice.CustomerDataService;
import nju.lighting.po.customer.CustomerPO;

import java.util.ArrayList;

public class CustomerDataService_Stub implements CustomerDataService {

    ArrayList<CustomerPO> customerList = new ArrayList<CustomerPO>();
    CustomerPO customer;

    public CustomerDataService_Stub(CustomerPO customer){
        this.customer = customer;
        customerList.add(customer);
    }

    public ArrayList<CustomerPO> getAllCustomer(){
        return customerList;
    }

    public CustomerPO findCustomer(String keyword){
        if(keyword.equals(customer.getName())||keyword.equals(String.valueOf(customer.getID()))){
            System.out.println("Find Succeed!\n");
            return customer;
        }
        else {
            System.out.println("Find Failed!\n");
            return null;
        }

    }

    public void insertCustomer(CustomerPO po){
        System.out.println("Insert Succeed!\n");
    }

    public void deleteCustomer(CustomerPO po){
        System.out.println("Delete Succeed!\n");
    }

    public void updateCustomer(CustomerPO po){
        System.out.println("Update Succeed!\n");
    }

    public int getNextCustomerID() { return customer.getID()+1; }

}
