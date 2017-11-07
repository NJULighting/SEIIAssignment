package nju.lighting.blservice.documentblservice;


import shared.DocType;
import nju.lighting.vo.viewtables.BusinessHistoryItemVO;
import nju.lighting.vo.viewtables.RevenueAndExpenditureVO;
import nju.lighting.vo.viewtables.SaleRecordItemVO;

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
