package nju.lighting.bl.accountbl;

import nju.lighting.blservice.accountblservice.AccountBLService;
import nju.lighting.vo.account.AccountLogVO;
import nju.lighting.vo.account.AccountVO;
import shared.AccountChangeType;
import shared.ResultMessage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created on 2017/10/22.
 * Description:
 * @author Liao
 */
@Deprecated
public class AccountBLService_Stub implements AccountBLService {

    @Override
    public List<AccountVO> getAccountList() {
        ArrayList<AccountVO> accountVOS = new ArrayList<>();
        accountVOS.add(getAccount("0"));
        accountVOS.add(getAccount("1"));
        return accountVOS;
    }

    @Override
    public ResultMessage addAccount(String id, double amount, String name) {
        if (name.equals("existed")) return ResultMessage.FAILURE;
        else return ResultMessage.SUCCESS;
    }

    @Override
    public List<AccountVO> findAccounts(String keyword) {
        if (keyword.contains("1"))
            return getAccountList().subList(0, 1);
        else return getAccountList();
    }

    @Override
    public AccountVO getAccount(String id) {
        ArrayList<AccountLogVO> list1 = new ArrayList<>();
        list1.add(new AccountLogVO(1, new Date(), 100, 8000, AccountChangeType.IN));
        list1.add(new AccountLogVO(1, new Date(), -100, 8100, AccountChangeType.OUT));
        list1.add(new AccountLogVO(1, new Date(), 3000, 11000, AccountChangeType.IN));
        list1.add(new AccountLogVO(1, new Date(), -8000, 3000, AccountChangeType.OUT));
        list1.add(new AccountLogVO(1, new Date(), 10, 3100, AccountChangeType.IN));
        list1.add(new AccountLogVO(1, new Date(), -20, 11000, AccountChangeType.OUT));
        ArrayList<AccountLogVO> list2 = new ArrayList<>();
        list2.addAll(list1);
        list2.addAll(list1);

        AccountVO accountVO;
        if (id.equals("0"))
            accountVO = new AccountVO("0001", "Test Account 0", 100000, list1);
        else
            accountVO = new AccountVO("0002", "Test Account 1", 1000000, list2);
        return accountVO;
    }

    @Override
    public ResultMessage deleteAccount(String id) {
        if (id.equals("0001") || id.equals("0002"))
            return ResultMessage.SUCCESS;
        return ResultMessage.FAILURE;
    }

    @Override
    public ResultMessage modifyAccount(String accountID, String newName) {
        if (!newName.equals("1111"))
            return ResultMessage.SUCCESS;
        return ResultMessage.FAILURE;
    }
}
