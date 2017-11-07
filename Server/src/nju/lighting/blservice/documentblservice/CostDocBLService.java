package nju.lighting.blservice.documentblservice;

import nju.lighting.po.doc.costdoc.CostDocPO;
import shared.ResultMessage;
import nju.lighting.vo.AccountVO;
import nju.lighting.vo.CostDocVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created on 2017/10/20.
 * Description:
 * @author Liao
 */
public interface CostDocBLService {
    CostDocVO createCostDoc() throws RemoteException;

    ResultMessage commitDoc(CostDocPO costDocPO) throws RemoteException;

    String getState(String id) throws RemoteException;

    CostDocVO getHistoryDoc(String id) throws RemoteException;

    ArrayList<AccountVO> getAccountList() throws RemoteException;
}