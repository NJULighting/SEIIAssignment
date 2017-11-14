package nju.lighting.bl.customerbl;


import java.util.ArrayList;

/**
 * Create on 11/12
 * Description:
 * @author 高梦婷
 */
public class CustomerManage implements CustomerInfo{
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
     * 临时添加 by iznauy
     * 为了让系统可以临时跑起来QAQ
     * @return
     */
    @Override
    public ArrayList<Customer> getCustomerList() {
        return null;
    }
}
