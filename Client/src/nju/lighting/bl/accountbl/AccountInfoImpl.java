package nju.lighting.bl.accountbl;

import nju.lighting.bl.utils.DataServiceFunction;
import nju.lighting.bl.utils.DataServiceSupplier;
import nju.lighting.dataservice.DataFactory;
import nju.lighting.dataservice.accountdataservice.AccountDataService;
import nju.lighting.vo.account.AccountVO;

import javax.naming.NamingException;
import java.rmi.RemoteException;
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
    public void updateAmount(String accountId, double total) {
        try {
            Account account = new Account(dataService.get(accountId));
            account.updateAmount(total);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public AccountVO getAccountByID(String accountID) {
        return DataServiceFunction.findByToEntity(accountID, dataService::get,
                accountPO -> new Account(accountPO).toVO());
    }
}
