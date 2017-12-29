package nju.lighting.data.customerdata;

import nju.lighting.dataservice.customerdataservice.CustomerDataService;
import nju.lighting.po.customer.CustomerPO;
import shared.CustomerGrade;
import shared.CustomerType;
import shared.ResultMessage;
import shared.TwoTuple;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDataService_Stub implements CustomerDataService {

    ArrayList<CustomerPO> customerList = new ArrayList<CustomerPO>();

    public CustomerDataService_Stub(){
        CustomerPO customer = new CustomerPO(1, CustomerType.SALESPERSON, CustomerGrade.ONE,"许瑞"
                ,"17015235309","江苏南京","210008","257649998@164.com"
                ,1000,230,0,"黄海");

        customerList.add(customer);

        customer = new CustomerPO(2, CustomerType.SUPPLIER, CustomerGrade.FIVE,"韩杨"
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

    @Override
    public TwoTuple<ResultMessage, Integer> insertCustomer(CustomerPO po) throws RemoteException {
        return null;
    }

    @Override
    public ResultMessage deleteCustomer(int id) throws RemoteException {
        return null;
    }

    @Override
    public ResultMessage updateCustomer(CustomerPO po) throws RemoteException {
        return null;
    }

    @Override
    public CustomerPO getCustomerById(int id) throws RemoteException {
        return null;
    }

    @Override
    public ResultMessage changeReceivable(int customerId, double amount) throws RemoteException {
        return null;
    }

    @Override
    public ResultMessage changePayable(int customerId, double amount) throws RemoteException {
        return null;
    }

    @Override
    public ResultMessage changeReceivableLimit(int customerId, double amount) throws RemoteException {
        return null;
    }

    @Override
    public List<CustomerPO> fuzzySearchByName(String key) throws RemoteException {
        return null;
    }

    @Override
    public List<CustomerPO> fuzzySearchByTelephone(String key) throws RemoteException {
        return null;
    }

    @Override
    public List<CustomerPO> fuzzySearchByAddress(String key) throws RemoteException {
        return null;
    }

    @Override
    public List<CustomerPO> fuzzySearchByGrade(String key) throws RemoteException {
        return null;
    }

    @Override
    public List<CustomerPO> fuzzySearchByEmail(String key) throws RemoteException {
        return null;
    }

    @Override
    public List<CustomerPO> fuzzySearchById(Integer id) throws RemoteException {
        return null;
    }
}
