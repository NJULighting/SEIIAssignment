package nju.lighting.bl.accountbl;

import nju.lighting.vo.account.AccountVO;
import shared.ResultMessage;

import java.util.ArrayList;

/**
 * Created on 2017/11/12.
 * Description:
 * @author Liao
 */
public class MockAccountInfo implements AccountInfo {
    @Override
    public ArrayList<AccountVO> getAccountList() {
        ArrayList<AccountVO> res = new ArrayList<>();
        res.add(new AccountVO("Account0", 1000, null));
        res.add(new AccountVO("Account1", 2000, null));
        return null;
    }

    @Override
    public ResultMessage updateAmount(String targetName, double total) {
        return ResultMessage.SUCCESS;
    }
}