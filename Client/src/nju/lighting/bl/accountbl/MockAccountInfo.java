package nju.lighting.bl.accountbl;

import nju.lighting.vo.account.AccountVO;
import shared.ResultMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2017/11/12.
 * Description:
 * @author Liao
 */
public class MockAccountInfo implements AccountInfo {
    @Override
    public List<AccountVO> getAccountList() {
        ArrayList<AccountVO> res = new ArrayList<>();
        res.add(new AccountVO("0001", "Account0", 1000, null));
        res.add(new AccountVO("0002", "Account1", 2000, null));
        return null;
    }

    @Override
    public ResultMessage updateAmount(String targetName, double total) {
        return ResultMessage.SUCCESS;
    }

    @Override
    public AccountVO getAccountByID(String accountID) {
        return null;
    }
}
