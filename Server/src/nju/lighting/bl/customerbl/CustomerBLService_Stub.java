package nju.lighting.bl.customerbl;

import nju.lighting.vo.*;
import nju.lighting.blservice.customerblservice.CustomerBLService;
import shared.CustomerType;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class CustomerBLService_Stub implements CustomerBLService{

    ArrayList<CustomerVO> customerAll;
    int id;
    public CustomerBLService_Stub(CustomerVO customer) throws RemoteException {
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

    //结束客户管理
    public void endCustomerManage() throws RemoteException{
        System.out.println("End the management of customer!");
    }
}