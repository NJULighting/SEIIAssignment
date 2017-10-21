package nju.lighting.blservice.customerblservice;

import nju.lighting.vo.*;

import java.util.ArrayList;

public class CustomerBLService_Stub implements CustomerBLService{

    ArrayList<CustomerVO> customerAll;
    int id;
    public CustomerBLService_Stub(CustomerVO customer){
        customerAll.add(customer);
        id = customer.getID();
    }

    //得到客户列表
    public ArrayList<CustomerVO> getCustomerList () {
        return customerAll;
    }

    //增加客户
    public ResultMessage addCustomer(CustomerVO customer){
        if(customer.getID() == id){
            return ResultMessage.FAILURE;
        }
        else return ResultMessage.SUCCESS;
    }

    //查找客户
    public ArrayList<CustomerVO> findCustomer(String keyword){
        if(keyword.equals(String.valueOf(id))){
            return customerAll;
        }
        else return null;
    }

    //删除客户
    public ResultMessage deleteCustomer(CustomerVO customer){
        if(customer.getID() == id){
            return ResultMessage.SUCCESS;
        }
        else return ResultMessage.FAILURE;
    }

    //更改客户信息
    public ResultMessage modifyCustomer(CustomerVO customer){
        if(customer.getID() == id){
            return ResultMessage.SUCCESS;
        }
        else return ResultMessage.FAILURE;
    }

    //结束客户管理
    public void endCustomerManage(){
        System.out.println("End the management of customer!");
    }
}