package nju.lighting.bl.customerbl;

import java.util.ArrayList;
/**
 * Create on 11/21
 * Description:
 * @author 高梦婷
 */
public class MockCustomer implements CustomerInfo{

    /**
     * 若增加客户应收，amount为正数，反之为负
     * @param amount
     */
    public void changeReceivable(String customerId,double amount){

    }

    /**
     * 若增加客户应付，amount为正数，反之为负
     * @param amount
     */
    public void changePayable(String customerId,double amount){

    }

    /**
     * 得到包含所有客户的列表
     * @return 对应的客户列表
     */
    public ArrayList<Customer> getCustomerList(){
        return new ArrayList<Customer>();
    }

}
