package nju.lighting.blservice.approvalblservice;



import shared.ResultMessage;
import nju.lighting.vo.HistoryDocVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created on 2017/10/21.
 * Description
 *
 * @author 陈俊宇
 */
public interface ApprovalBLService {
    ArrayList<HistoryDocVO> getDocumentList()throws RemoteException;

    ResultMessage approve(HistoryDocVO vo)throws RemoteException;

    ResultMessage approveAll(ArrayList<HistoryDocVO> voList) throws RemoteException;

    ResultMessage reject(HistoryDocVO vo) throws RemoteException;

    ResultMessage save(HistoryDocVO vo) throws RemoteException;

    ResultMessage saveAndApprove(HistoryDocVO vo) throws RemoteException;
}
