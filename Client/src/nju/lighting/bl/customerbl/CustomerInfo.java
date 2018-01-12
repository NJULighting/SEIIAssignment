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
}
