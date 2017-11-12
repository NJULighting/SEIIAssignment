package nju.lighting.blservice.customerblservice;

import nju.lighting.vo.CustomerVO;
import shared.CustomerType;
import shared.ResultMessage;

import java.util.ArrayList;

public interface CustomerBLService {

    //得到客户列表
    ArrayList<CustomerVO> getCustomerList() ;

    //请求增加客户，得到是否可修改应收额度
    boolean askForCreateCustomer(String userId) ;

    //增加客户
    ResultMessage createCustomer(CustomerVO vo) ;

    //查找客户
    ArrayList<CustomerVO> findCustomer(String keyword) ;

    //删除客户
    ResultMessage deleteCustomer(CustomerVO customer) ;

    //更改客户信息
    ResultMessage modifyCustomer(CustomerVO customer) ;

    //根据客户ID寻找客户
    CustomerVO findCustomerByID(int id) ;

    //根据客户类型寻找客户
    ArrayList<CustomerVO> findCustomerByType(CustomerType type) ;

    //获取下一位客户编号
    int getNextCustomerID() ;

    //请求修改客户，得到是否可修改应收额度
    boolean askForModifyCustomer(String userId) ;
}
