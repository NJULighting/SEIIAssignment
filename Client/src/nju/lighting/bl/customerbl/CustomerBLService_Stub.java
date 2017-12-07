package nju.lighting.bl.customerbl;

import nju.lighting.blservice.customerblservice.CustomerBLService;
import shared.CustomerChangeInfo;
import shared.ResultMessage;
import nju.lighting.vo.CustomerVO;
import shared.CustomerType;

import java.util.ArrayList;
import java.util.List;

@Deprecated
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
    public List<CustomerVO> getCustomerList()  {
        return customerAll;
    }


    //增加客户
    public ResultMessage createCustomer(CustomerVO vo)  {
        if (vo.getID() != id)
            return ResultMessage.SUCCESS;
        else
            return ResultMessage.FAILURE;
    }

    @Override
    public ResultMessage changeReceivableLimit(int customerID, double newLimit) {
        return null;
    }

    //查找客户
    public List<CustomerVO> search(String keyword)  {
        if (keyword.equals(String.valueOf(id))) {
            return customerAll;
        } else return null;
    }

    //删除客户
    public ResultMessage deleteCustomer(int customerID)  {
        return ResultMessage.SUCCESS;
    }

    //更改客户信息
    public ResultMessage changeCustomer(CustomerChangeInfo changeInfo)  {
        return ResultMessage.SUCCESS;
    }


    //根据客户ID寻找客户
    public CustomerVO findCustomerByID(int id)  {
        if (id == this.id) {
            return customer;
        } else return null;
    }

    //根据客户类型寻找客户
    public List<CustomerVO> findCustomerByType(CustomerType type)  {
        if (type == customer.getType()) {
            return customerAll;
        } else return null;
    }

    @Override
    public ResultMessage changeReceivableLimit(int customerID, double newLimit) {
        return null;
    }
}