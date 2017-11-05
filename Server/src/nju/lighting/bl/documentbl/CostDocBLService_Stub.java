package nju.lighting.bl.documentbl;

import nju.lighting.bl.accountbl.AccountBLService_Stub;
import nju.lighting.blservice.accountblservice.AccountBLService;
import nju.lighting.blservice.documentblservice.CostDocBLService;
import nju.lighting.po.CostDocItemPO;
import nju.lighting.po.CostDocItemType;
import nju.lighting.po.CostDocPO;
import nju.lighting.po.ResultMessage;
import nju.lighting.vo.AccountVO;
import nju.lighting.vo.CostDocVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created on 2017/10/22.
 * Description:
 * @author Liao
 */
public class CostDocBLService_Stub implements CostDocBLService {
    @Override
    public CostDocVO createCostDoc() throws RemoteException {
        ArrayList<String> accounts = new ArrayList<>();
        accounts.add("account0");
        accounts.add("account1");
        ArrayList<CostDocItemPO> items = new ArrayList<>();
        CostDocItemPO item0 = new CostDocItemPO(CostDocItemType.ACCOMMODATION, 1000, "Excited!");
        CostDocItemPO item1 = new CostDocItemPO(CostDocItemType.TRANSPORTATION, 1500, "I'm angry");
        items.add(item0);
        items.add(item1);
        return new CostDocVO("0000", accounts, items, 2500);
    }

    @Override
    public ResultMessage commitDoc(CostDocPO costDocPO) throws RemoteException {
        return costDocPO == null ? ResultMessage.FAILURE : ResultMessage.SUCCESS;
    }

    @Override
    public String getState(String id) throws RemoteException {
        return id.isEmpty() ? "No such document" : "passed";
    }

    @Override
    public CostDocVO getHistoryDoc(String id) throws RemoteException {
        return createCostDoc();
    }

    @Override
    public ArrayList<AccountVO> getAccountList() throws RemoteException {
        AccountBLService accountBLService = new AccountBLService_Stub();
        return accountBLService.getAccountList();
    }
}
