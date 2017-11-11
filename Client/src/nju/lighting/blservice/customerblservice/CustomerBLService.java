package nju.lighting.blservice.customerblservice;

import shared.ResultMessage;
import nju.lighting.vo.CustomerVO;
import shared.CustomerType;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface CustomerBLService {

    //得到客户列表
    ArrayList<CustomerVO> getCustomerList() throws RemoteException;

    //请求增加客户，得到是否可修改应收额度
    boolean askForCreateCustomer(String userId) throws RemoteException;

    //增加客户
    ResultMessage createCustomer(CustomerVO vo) throws RemoteException;

    //查找客户
    ArrayList<CustomerVO> findCustomer(String keyword) throws RemoteException;

    //删除客户
    ResultMessage deleteCustomer(CustomerVO customer) throws RemoteException;

    //更改客户信息
    ResultMessage modifyCustomer(CustomerVO customer) throws RemoteException;

    //根据客户ID寻找客户
    CustomerVO findCustomerByID(int id) throws RemoteException;

    //根据客户类型寻找客户
    ArrayList<CustomerVO> findCustomerByType(CustomerType type) throws RemoteException;

    //获取下一位客户编号
    int getNextCustomerID() throws RemoteException;

    //请求修改客户，得到是否可修改应收额度
    boolean askForModifyCustomer(String userId) throws RemoteException;
}
