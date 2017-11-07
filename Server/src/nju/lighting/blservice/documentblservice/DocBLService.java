package nju.lighting.blservice.documentblservice;

import nju.lighting.vo.DocVO;

import nju.lighting.vo.viewtables.BusinessHistoryItemVO;
import nju.lighting.vo.viewtables.RevenueAndExpenditureVO;
import nju.lighting.vo.viewtables.SaleRecordItemVO;
import shared.*;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

public interface DocBLService {

    DocVO createDoc(DocType type) throws RemoteException;

    ResultMessage commitDoc(DocVO doc) throws RemoteException;

    ArrayList<DocVO> getDoc(DocumentFilter filter) throws RemoteException;

    ArrayList<SaleRecordItemVO> findSaleRecords(SaleRecordFilter filter) throws RemoteException;

    ArrayList<BusinessHistoryItemVO> findDocuments(DocumentFilter filter) throws RemoteException;

    RevenueAndExpenditureVO findRevenueAndExpenditure(Date startDate, Date endDate) throws RemoteException;

    ResultMessage redFlush(DocVO docVO) throws RemoteException;

    DocVO redFlushAndCopy(DocVO target) throws RemoteException;

}
