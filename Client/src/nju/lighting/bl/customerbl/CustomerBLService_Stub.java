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

    public CustomerBLService_Stub(CustomerVO customer) throws RemoteException {
        customerAll.add(customer);
        this.customer = customer;
        id = customer.getID();
    }

    //得到客户列表
    public ArrayList<CustomerVO> getCustomerList() throws RemoteException {
        return customerAll;
    }

    //请求增加客户，得到是否可修改应收额度
    public boolean askForCreateCustomer(String userId) throws RemoteException {
        return true;
    }


    //增加客户
    public ResultMessage createCustomer(CustomerVO vo) throws RemoteException {
        if (vo.getID() != id)
            return ResultMessage.SUCCESS;
        else
            return ResultMessage.FAILURE;
    }

    //查找客户
    public ArrayList<CustomerVO> findCustomer(String keyword) throws RemoteException {
        if (keyword.equals(String.valueOf(id))) {
            return customerAll;
        } else return null;
    }

    //删除客户
    public ResultMessage deleteCustomer(CustomerVO customer) throws RemoteException {
        if (customer.getID() == id) {
            return ResultMessage.SUCCESS;
        } else return ResultMessage.FAILURE;
    }

    //请求修改客户，得到是否可修改应收额度
    public boolean askForModifyCustomer(String userId) throws RemoteException {
        return true;
    }

    //更改客户信息
    public ResultMessage modifyCustomer(CustomerVO customer) throws RemoteException {
        if (customer.getID() == id) {
            return ResultMessage.SUCCESS;
        } else return ResultMessage.FAILURE;
    }

    //获取下一位客户编号
    public int getNextCustomerID() throws RemoteException {
        return id + 1;
    }


    //根据客户ID寻找客户
    public CustomerVO findCustomerByID(int id) throws RemoteException {
        if (id == this.id) {
            return customer;
        } else return null;
    }

    //根据客户类型寻找客户
    public ArrayList<CustomerVO> findCustomerByType(CustomerType type) throws RemoteException {
        if (type == customer.getType()) {
            return customerAll;
        } else return null;
    }

}