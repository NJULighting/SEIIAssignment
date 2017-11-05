package nju.lighting.bl.documentbl;

import nju.lighting.bl.accountbl.AccountBLService_Stub;
import nju.lighting.blservice.accountblservice.AccountBLService;
import nju.lighting.bl.customerbl.CustomerBLService_Stub;
import nju.lighting.blservice.documentblservice.AccountIODocBLService;
import nju.lighting.po.AccountIODocType;
import nju.lighting.po.AccountTransferItemPO;
import nju.lighting.po.ResultMessage;
import nju.lighting.vo.AccountIODocVO;
import nju.lighting.vo.AccountVO;
import nju.lighting.vo.CustomerVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created on 2017/10/22.
 * Description:
 * @author Liao
 */
public class AcccountIODocBLService_Stub implements AccountIODocBLService {
    @Override
    public AccountIODocVO createAccountInOut() throws RemoteException {
        AccountBLService_Stub stub = new AccountBLService_Stub();
        AccountTransferItemPO transferList = new AccountTransferItemPO("Test Account", 10000, "Naive!");
        return new AccountIODocVO("0000", AccountIODocType.IN, "Customer", transferList, 10000);
    }

    @Override
    public ResultMessage commitDoc(AccountIODocVO docVO) throws RemoteException {
        return docVO == null ? ResultMessage.FAILURE : ResultMessage.SUCCESS;
    }

    @Override
    public String getState(String id) throws RemoteException {
        return "Passed";
    }

    @Override
    public AccountIODocVO getHistoryDoc(String id) throws RemoteException {
        AccountTransferItemPO transferList = new AccountTransferItemPO("Test Account", 10000, "Naive!");
        return new AccountIODocVO("0001", AccountIODocType.IN, "Customer", transferList, 100);
    }

    @Override
    public ArrayList<CustomerVO> getCustomerList() throws RemoteException {
        CustomerBLService_Stub stub = new CustomerBLService_Stub(null);
        return stub.getCustomerList();
    }

    @Override
    public ArrayList<AccountVO> getAccountList() throws RemoteException {
        AccountBLService accountBLService = new AccountBLService_Stub();
        return accountBLService.getAccountList();
    }
}
