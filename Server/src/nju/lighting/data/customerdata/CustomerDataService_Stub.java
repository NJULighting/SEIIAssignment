package nju.lighting.data.customerdata;

import nju.lighting.dataservice.customerdataservice.CustomerDataService;
import nju.lighting.po.CustomerPO;

import java.util.ArrayList;

public class CustomerDataService_Stub implements CustomerDataService {

    ArrayList<CustomerPO> customerList = new ArrayList<CustomerPO>();
    CustomerPO customer;

    public CustomerDataService_Stub(CustomerPO customer){
        this.customer = customer;
        customerList.add(customer);
    }

    public ArrayList<CustomerPO> getAll(){
        return customerList;
    }

    public CustomerPO find(String keyword){
        if(keyword.equals(customer.getName())||keyword.equals(String.valueOf(customer.getID()))){
            System.out.println("Find Succeed!\n");
            return customer;
        }
        else {
            System.out.println("Find Failed!\n");
            return null;
        }

    }

    public void insert(CustomerPO po){
        System.out.println("Insert Succeed!\n");
    }

    public void delete(CustomerPO po){
        System.out.println("Delete Succeed!\n");
    }

    public void update(CustomerPO po){
        System.out.println("Update Succeed!\n");
    }

}
