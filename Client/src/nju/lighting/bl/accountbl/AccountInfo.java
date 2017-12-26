package nju.lighting.bl.accountbl;

import nju.lighting.vo.account.AccountVO;
import shared.ResultMessage;

import java.util.List;

/**
 * Created on 2017/11/7.
 * Description: 该接口为其他需要账户信息的模块提供服务
 * @author Liao
 */
public interface AccountInfo {
    List<AccountVO> getAccountList();

    ResultMessage updateAmount(String accountId, double total);

    AccountVO getAccountByID(String accountID);
}
