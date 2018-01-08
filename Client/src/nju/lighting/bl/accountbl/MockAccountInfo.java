package nju.lighting.bl.accountbl;

import nju.lighting.vo.account.AccountVO;
import shared.ResultMessage;

/**
 * Created on 2017/11/12.
 * Description:
 * @author Liao
 */
public class MockAccountInfo implements AccountInfo {

    @Override
    public ResultMessage addAmount(String accountId, double total) {
        return ResultMessage.SUCCESS;
    }

    @Override
    public AccountVO getAccountByID(String accountID) {
        return null;
    }
}
