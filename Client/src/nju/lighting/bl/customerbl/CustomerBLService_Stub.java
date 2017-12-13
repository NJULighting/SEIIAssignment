package nju.lighting.bl.customerbl;

import nju.lighting.blservice.customerblservice.CustomerBLService;
import shared.CustomerChangeInfo;
import shared.CustomerGrade;
import shared.ResultMessage;
import nju.lighting.vo.CustomerVO;
import shared.CustomerType;

import java.util.*;

@Deprecated
public class CustomerBLService_Stub implements CustomerBLService {

    private List<CustomerVO> customerAll = new ArrayList<CustomerVO>();

    private CustomerVO customerVO1;
    private CustomerVO customerVO2;
    private CustomerVO customerVO3;
    private CustomerVO customerVO4;

    public CustomerBLService_Stub()  {
        customerVO1 = new CustomerVO(000001,CustomerType.SALESPERSON, CustomerGrade.ONE,"王伟光","1355544268","新街口希望小区189号","232177","hjjj7889@163.com",2000,100,78.9,"李杰");
        customerVO2 = new CustomerVO(000002,CustomerType.SALESPERSON, CustomerGrade.FIVE,"苏夏","1738844268","安徽省淮南市某某县某小区162号2单元","232837","s9nf889@qq.com",2000,1000,0,"李杰");
        customerVO3 = new CustomerVO(000003,CustomerType.SUPPLIER, CustomerGrade.FOUR,"顾海良","122-2817293","大谷堆","232177","",2000,100,1200,"李杰");
        customerVO4 = new CustomerVO(000004,CustomerType.SUPPLIER, CustomerGrade.TWO,"张磊","1255544290","","","",2000,100,78.9,"苏良");
        customerAll.add(customerVO1);
        customerAll.add(customerVO2);
        customerAll.add(customerVO3);
        customerAll.add(customerVO4);
    }

    //得到客户列表
    public List<CustomerVO> getCustomerList()  {
        return customerAll;
    }


    //增加客户
    public ResultMessage createCustomer(CustomerVO vo)  {
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage changeReceivableLimit(int customerID, double newLimit) {
        return ResultMessage.SUCCESS;
    }

    //查找客户
    public List<CustomerVO> search(String keyword)  {
        List<CustomerVO> customer = customerAll;
        if (keyword.equals("李杰")) {
            customer.remove(3);
            return customer;
        } else if(keyword.equals("苏良")){
            customer.remove(0);
            customer.remove(1);
            customer.remove(2);
            return customer;
        } else if(keyword.equals("销售商")){
            customer.remove(2);
            customer.remove(3);
            return customer;
        } else if(keyword.equals("供应商")){
            customer.remove(0);
            customer.remove(1);
            return customer;
        }else
            return null;
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
        switch (id){
            case 000001:
                return customerVO1;
            case 000002:
                return customerVO2;
            case 000003:
                return customerVO3;
            case 000004:
                return customerVO4;
            default:
                return null;
        }
    }

    //根据客户类型寻找客户
    public List<CustomerVO> findCustomerByType(CustomerType type)  {
        List<CustomerVO> customer = customerAll;
        if (type == CustomerType.SALESPERSON) {
            customer.remove(2);
            customer.remove(3);
            return customer;
        } else if(type == CustomerType.SUPPLIER){
            customer.remove(0);
            customer.remove(1);
            return customer;
        }else
            return null;
    }
}