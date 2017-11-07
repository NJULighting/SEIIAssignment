package nju.lighting.dataservice.accountdataservice;

import nju.lighting.po.account.AccountPO;
import shared.ResultMessage;

import java.util.ArrayList;

/**
 * Created on 2017/10/22.
 * Description:
 * @author Liao
 */
public interface AccountDataService {
    ResultMessage insert(AccountPO po);

    ArrayList<AccountPO> find(String keyword);

    AccountPO get(String id);

    ArrayList<AccountPO> getAll();

    ResultMessage delete(AccountPO po);

    void finish();

    void init();

    ResultMessage update(AccountPO po);
}
