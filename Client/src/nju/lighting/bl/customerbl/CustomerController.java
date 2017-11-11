package nju.lighting.bl.customerbl;

import nju.lighting.blservice.customerblservice.CustomerBLService;
import shared.ResultMessage;
import nju.lighting.vo.CustomerVO;
import shared.CustomerType;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class CustomerController implements CustomerBLService {


    //得到客户列表
    public ArrayList<CustomerVO> getCustomerList() throws RemoteException {
        return null;
    }

    //请求增加客户，得到是否可修改应收额度
    public boolean askForCreateCustomer(String userId) throws RemoteException {
        return true;
    }

    //增加客户
    public ResultMessage createCustomer(CustomerVO vo) throws RemoteException {
        return ResultMessage.SUCCESS;
    }

    //查找客户
    public ArrayList<CustomerVO> findCustomer(String keyword) throws RemoteException {
        return null;
    }

    //删除客户
    public ResultMessage deleteCustomer(CustomerVO customer) throws RemoteException {
        return ResultMessage.SUCCESS;
    }

    //更改客户信息
    public ResultMessage modifyCustomer(CustomerVO customer) throws RemoteException {
        return ResultMessage.SUCCESS;
    }

    //根据客户ID寻找客户
    public CustomerVO findCustomerByID(int id) throws RemoteException {
        return null;
    }

    //根据客户类型寻找客户
    public ArrayList<CustomerVO> findCustomerByType(CustomerType type) throws RemoteException {
        return null;
    }

    //获取下一位客户编号
    public int getNextCustomerID() throws RemoteException {
        return 000001;
    }

    //请求修改客户，得到是否可修改应收额度
    public boolean askForModifyCustomer(String userId) throws RemoteException {
        return true;
    }

}
