package nju.lighting.bl.accountbl;

import nju.lighting.bl.utils.DataServiceSupplier;
import nju.lighting.dataservice.DataFactory;
import nju.lighting.dataservice.accountdataservice.AccountDataService;
import nju.lighting.vo.account.AccountVO;
import shared.ResultMessage;

import javax.naming.NamingException;
import java.util.List;

/**
 * Created on 2017/12/14.
 * Description:
 * @author Liao
 */
public class AccountInfoImpl implements AccountInfo {
    private AccountDataService dataService;

    public AccountInfoImpl() {
        try {
            dataService = DataFactory.getDataBase(AccountDataService.class);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<AccountVO> getAccountList() {
        return DataServiceSupplier.getAll(dataService::getAll, accountPO -> new Account(accountPO).toVO());
    }

    @Override
    public ResultMessage updateAmount(String targetName, double total) {
        return null;
    }
}
