package nju.lighting.bl.customerbl;

import shared.ResultMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Create on 11/21
 * Description:
 * @author 高梦婷
 */
public class MockCustomer implements CustomerInfo {

    /**
     * 若增加客户应收，amount为正数，反之为负
     * @param customerId
     * @param amount
     */
    public ResultMessage changeReceivable(int customerId, double amount) {
        return ResultMessage.FAILURE;
    }

    /**
     * 若增加客户应付，amount为正数，反之为负
     * @param customerId
     * @param amount
     */
    public ResultMessage changePayable(int customerId, double amount) {
        return ResultMessage.FAILURE;
    }

    /**
     * 得到包含所有客户的列表
     * @return 对应的客户列表
     */
    public List<Customer> getCustomerList() {
        return new ArrayList<Customer>();
    }

}
