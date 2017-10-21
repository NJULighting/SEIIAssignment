package nju.lighting.blservice.documentblservice;


import nju.lighting.po.DocType;
import nju.lighting.po.HistoryDocPO;

import java.rmi.RemoteException;

/**
 * Created on 2017/10/21.
 * Description
 *
 * @author 陈俊宇
 */
public interface ViewTablesBLService {

    HistoryDocVO findSaleRecords(long startDate, long endDate, String Commodity, String customer, String user, String repository) throws RemoteException;

    HistoryDocVO findDocuments(long startDate, long endDate, DocType type, String customer, String user, String repository) throws RemoteException;

    LossAndGainPO findLossAndGain(long startDate, long endDate) throws RemoteException;

}
