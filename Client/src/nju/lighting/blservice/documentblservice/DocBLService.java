package nju.lighting.blservice.documentblservice;

import nju.lighting.vo.DocVO;
import nju.lighting.vo.doc.historydoc.HistoryDocVO;
import nju.lighting.vo.viewtables.BusinessHistoryItemVO;
import nju.lighting.vo.viewtables.RevenueAndExpenditureVO;
import nju.lighting.vo.viewtables.SaleRecordItemVO;
import shared.DocType;
import shared.DocumentFilter;
import shared.ResultMessage;
import shared.SaleRecordFilter;

import java.util.ArrayList;
import java.util.Date;

public interface DocBLService {

    DocVO createDoc(DocType type) ;

    ResultMessage commitDoc(DocVO doc) ;

    ArrayList<HistoryDocVO> findDocuments(DocumentFilter filter);

    ArrayList<SaleRecordItemVO> findSaleRecords(SaleRecordFilter filter) ;

    ArrayList<BusinessHistoryItemVO> findBusinessHistory(DocumentFilter filter) ;

    RevenueAndExpenditureVO findRevenueAndExpenditure(Date startDate, Date endDate) ;

    ResultMessage redFlush(DocVO docVO) ;

    DocVO redFlushAndCopy(DocVO target) ;

}
