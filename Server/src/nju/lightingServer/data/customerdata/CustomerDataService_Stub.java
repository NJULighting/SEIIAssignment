package nju.lightingServer.data.customerdata;

import nju.lighting.dataservice.customerdataservice.CustomerDataService;
import nju.lighting.po.customer.CustomerPO;
import shared.CustomerGrade;
import shared.CustomerType;

import java.util.ArrayList;

public class CustomerDataService_Stub implements CustomerDataService {

    ArrayList<CustomerPO> customerList = new ArrayList<CustomerPO>();

    public CustomerDataService_Stub(){
        CustomerPO customer = new CustomerPO(000001, CustomerType.SALESPERSON, CustomerGrade.ONE,"许瑞"
                ,"17015235309","江苏南京","210008","257649998@164.com"
                ,1000,230,0,"黄海");

        customerList.add(customer);

        customer = new CustomerPO(000002, CustomerType.SUPPLIER, CustomerGrade.FIVE,"韩杨"
                ,"18445238801","江苏苏州","215002","1036496770@164.com"
                ,1000,20,900,"黄海");

        customerList.add(customer);
    }

    public ArrayList<CustomerPO> getAllCustomer(){
        return customerList;
    }

    public ArrayList<CustomerPO> findCustomer(String keyword){
        ArrayList<CustomerPO> customers = new ArrayList<CustomerPO>();

        if(keyword.equals("000001")||keyword.equals("许瑞")||keyword.equals("江苏南京")||keyword.equals("南京")){
            System.out.println("Find Succeeded!\n");
            customers.add(customerList.get(0));
            return customers;
        }
        else if(keyword.equals("000002")||keyword.equals("韩杨")||keyword.equals("江苏苏州")||keyword.equals("苏州")){
            System.out.println("Find Succeeded!\n");
            customers.add(customerList.get(1));
            return customers;
        }
        else if(keyword.equals("黄海")){
            System.out.println("Find Succeeded!\n");
            return customerList;
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

    public int getNextCustomerID() { return 000003; }

    /**
     * 若增加客户应收，amount为正数，反之为负
     * @param amount
     */
    public void changeReceivable(String customerId,double amount){
        System.out.println("Change Receivable Succeed!\n");
    }

    /**
     * 若增加客户应付，amount为正数，反之为负
     * @param amount
     */
    public void changePayable(String customerId,double amount){
        System.out.println("Change Payable Succeed!\n");
    }

}
