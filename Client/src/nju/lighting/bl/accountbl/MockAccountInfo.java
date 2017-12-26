package nju.lighting.bl.accountbl;

import nju.lighting.vo.account.AccountVO;

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
    public void updateAmount(String accountId, double total) {

    }

    @Override
    public AccountVO getAccountByID(String accountID) {
        return null;
    }
}
