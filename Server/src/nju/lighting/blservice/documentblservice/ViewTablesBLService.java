package nju.lighting.blservice.documentblservice;


import nju.lighting.po.DocType;
import nju.lighting.po.HistoryDocPO;
import nju.lighting.vo.BusinessHistoryItemVO;
import nju.lighting.vo.HistoryDocVO;
import nju.lighting.vo.RevenueAndExpenditureVO;
import nju.lighting.vo.SaleRecordItemVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created on 2017/10/21.
 * Description 查看***表
 *
 * @author 陈俊宇
 */
public interface ViewTablesBLService {

    ArrayList<SaleRecordItemVO> findSaleRecords(long startDate, long endDate, String Commodity, String customer, String user, String repository) throws RemoteException;

    ArrayList<BusinessHistoryItemVO> findDocuments(long startDate, long endDate, DocType type, String customer, String user, String repository) throws RemoteException;

    RevenueAndExpenditureVO findRevenueAndExpenditure(long startDate, long endDate) throws RemoteException;

}
