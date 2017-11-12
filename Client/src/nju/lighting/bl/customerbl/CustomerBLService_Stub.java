package nju.lighting.bl.customerbl;

import nju.lighting.blservice.customerblservice.CustomerBLService;
import shared.ResultMessage;
import nju.lighting.vo.CustomerVO;
import shared.CustomerType;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class CustomerBLService_Stub implements CustomerBLService {

    ArrayList<CustomerVO> customerAll;
    CustomerVO customer;
    int id;

    public CustomerBLService_Stub(CustomerVO customer)  {
        customerAll.add(customer);
        this.customer = customer;
        id = customer.getID();
    }

    //得到客户列表
    public ArrayList<CustomerVO> getCustomerList()  {
        return customerAll;
    }

    //请求增加客户，得到是否可修改应收额度
    public boolean askForCreateCustomer(String userId)  {
        return true;
    }


    //增加客户
    public ResultMessage createCustomer(CustomerVO vo)  {
        if (vo.getID() != id)
            return ResultMessage.SUCCESS;
        else
            return ResultMessage.FAILURE;
    }

    //查找客户
    public ArrayList<CustomerVO> findCustomer(String keyword)  {
        if (keyword.equals(String.valueOf(id))) {
            return customerAll;
        } else return null;
    }

    //删除客户
    public ResultMessage deleteCustomer(CustomerVO customer)  {
        if (customer.getID() == id) {
            return ResultMessage.SUCCESS;
        } else return ResultMessage.FAILURE;
    }

    //请求修改客户，得到是否可修改应收额度
    public boolean askForModifyCustomer(String userId)  {
        return true;
    }

    //更改客户信息
    public ResultMessage modifyCustomer(CustomerVO customer)  {
        if (customer.getID() == id) {
            return ResultMessage.SUCCESS;
        } else return ResultMessage.FAILURE;
    }

    //获取下一位客户编号
    public int getNextCustomerID()  {
        return id + 1;
    }


    //根据客户ID寻找客户
    public CustomerVO findCustomerByID(int id)  {
        if (id == this.id) {
            return customer;
        } else return null;
    }

    //根据客户类型寻找客户
    public ArrayList<CustomerVO> findCustomerByType(CustomerType type)  {
        if (type == customer.getType()) {
            return customerAll;
        } else return null;
    }

}