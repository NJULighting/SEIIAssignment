package nju.lighting.blservice.accountblservice;

import nju.lighting.vo.account.AccountVO;
import shared.ResultMessage;

import java.util.List;

/**
 * Created on 2017/10/20.
 * Description:
 * @author Liao
 */
public interface AccountBLService {
    List<AccountVO> getAccountList();

    ResultMessage addAccount(String name, double amount, String id);

    List<AccountVO> findAccounts(String keyword);

    AccountVO getAccount(String id);

    ResultMessage deleteAccount(String id);

    ResultMessage modifyAccount(String accountID, String newName);
}
