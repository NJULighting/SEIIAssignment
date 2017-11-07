package nju.lighting.blservice.documentblservice;

import nju.lighting.vo.BusinessHistoryItemVO;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.RevenueAndExpenditureVO;
import nju.lighting.vo.SaleRecordItemVO;
import shared.*;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

public interface DocBLService {

    DocVO createDoc(DocType type) throws RemoteException;

    ResultMessage commitDoc(DocVO doc) throws RemoteException;

    ArrayList<DocVO> getDoc(DocFilter filter) throws RemoteException;

    ArrayList<SaleRecordItemVO> findSaleRecords(SaleRecordFilter filter) throws RemoteException;

    ArrayList<BusinessHistoryItemVO> findDocuments(DocumentFilter filter) throws RemoteException;

    RevenueAndExpenditureVO findRevenueAndExpenditure(Date startDate, Date endDate) throws RemoteException;

    ResultMessage redFlush(DocVO docVO) throws RemoteException;

    DocVO redFlushAndCopy(DocVO target) throws RemoteException;

}
