package nju.lighting.bl.customerbl;

import shared.ResultMessage;

import java.util.List;

/**
 * Create on 11/12
 * Description:
 * @author 高梦婷
 */
public interface CustomerInfo {
    /**
     * 若增加客户应收，amount为正数，反之为负
     * @param customerId id of customer
     * @param amount     amount of receivable
     */
    ResultMessage changeReceivable(int customerId, double amount);

    /**
     * 若增加客户应付，amount为正数，反之为负
     * @param customerId id of target customer
     * @param amount     amount of payable
     */
    ResultMessage changePayable(int customerId, double amount);

    /**
     * 得到包含所有客户的列表
     * @return 对应的客户列表
     */
    List<Customer> getCustomerList();
}
