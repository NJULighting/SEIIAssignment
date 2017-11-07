package nju.lighting.bl.customerbl;

import nju.lighting.vo.*;
import nju.lighting.blservice.customerblservice.CustomerBLService;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class CustomerBLService_Stub implements CustomerBLService{

    ArrayList<CustomerVO> customerAll;
    CustomerVO customer;
    int id;
    public CustomerBLService_Stub(CustomerVO customer) throws RemoteException {
        this.customer = customer;
        customerAll.add(customer);
        id = customer.getID();
    }

    //得到客户列表
    public ArrayList<CustomerVO> getCustomerList () throws RemoteException {
        return customerAll;
    }

    //增加客户
    public ResultMessage addCustomer(CustomerVO customer) throws RemoteException{
        if(customer.getID() == id){
            return ResultMessage.FAILURE;
        }
        else return ResultMessage.SUCCESS;
    }

    //查找客户
    public ArrayList<CustomerVO> findCustomer(String keyword) throws RemoteException{
        if(keyword.equals(String.valueOf(id))){
            return customerAll;
        }
        else return null;
    }

    //删除客户
    public ResultMessage deleteCustomer(CustomerVO customer) throws RemoteException{
        if(customer.getID() == id){
            return ResultMessage.SUCCESS;
        }
        else return ResultMessage.FAILURE;
    }

    //更改客户信息
    public ResultMessage modifyCustomer(CustomerVO customer) throws RemoteException{
        if(customer.getID() == id){
            return ResultMessage.SUCCESS;
        }
        else return ResultMessage.FAILURE;
    }


    //根据客户ID寻找客户
    public CustomerVO findCustomerByID(int id) throws RemoteException{
        if(id==customer.getID())
            return customer;
        else
            return null;
    }

    //根据客户类型寻找客户
    public ArrayList<CustomerVO> findCustomerByType(CustomerType type) throws RemoteException{
        if(type==customer.getType())
            return customerAll;
        else return null;
    }

}