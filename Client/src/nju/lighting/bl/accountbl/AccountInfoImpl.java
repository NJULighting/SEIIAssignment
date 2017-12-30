package nju.lighting.bl.accountbl;

import nju.lighting.bl.utils.DataServiceFunction;
import nju.lighting.dataservice.DataFactory;
import nju.lighting.dataservice.accountdataservice.AccountDataService;
import nju.lighting.vo.account.AccountVO;
import shared.ResultMessage;

import javax.naming.NamingException;
import java.rmi.RemoteException;

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
    public ResultMessage updateAmount(String accountId, double total) {
        try {
            Account account = new Account(dataService.get(accountId));
            return account.updateAmount(total);
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.FAILURE;
        }
    }

    @Override
    public AccountVO getAccountByID(String accountID) {
        return DataServiceFunction.findByToEntity(accountID, dataService::get,
                accountPO -> new Account(accountPO).toVO());
    }
}
