package nju.lighting.blservice.documentblservice;

import nju.lighting.vo.DocVO;
import nju.lighting.vo.doc.historydoc.HistoryDocVO;
import nju.lighting.vo.viewtables.BusinessConditionItemVO;
import nju.lighting.vo.viewtables.BusinessHistoryItemVO;
import nju.lighting.vo.viewtables.SalesDetailVO;
import shared.*;

import java.util.ArrayList;
import java.util.Date;

public interface DocBLService {

    DocVO createDoc(DocType type);

    TwoTuple<String,ResultMessage> commitDoc(DocVO doc);

    ArrayList<HistoryDocVO> findDocuments(DocumentFilter filter);

    ArrayList<BusinessConditionItemVO> findSaleRecords(BusinessConditionFilter filter);

    ArrayList<BusinessHistoryItemVO> findBusinessHistory(DocumentFilter filter);

    SalesDetailVO findRevenueAndExpenditure(Date startDate, Date endDate);

    ResultMessage redFlush(DocVO docVO);

    DocVO redFlushAndCopy(DocVO target);

}
