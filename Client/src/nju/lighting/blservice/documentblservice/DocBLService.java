package nju.lighting.blservice.documentblservice;

import nju.lighting.vo.DocVO;
import nju.lighting.vo.UserVO;
import nju.lighting.vo.doc.historydoc.HistoryDocVO;
import nju.lighting.vo.viewtables.BusinessConditionItemVO;
import nju.lighting.vo.viewtables.BusinessHistoryItemVO;
import nju.lighting.vo.viewtables.SalesDetailVO;
import shared.*;

import java.util.Date;
import java.util.List;

public interface DocBLService {

    TwoTuple<String,ResultMessage> commitDoc(DocVO doc);

    List<HistoryDocVO> findDocuments(DocumentFilter filter);

    List<BusinessConditionItemVO> findSaleRecords(BusinessConditionFilter filter);

    List<BusinessHistoryItemVO> findBusinessHistory(DocumentFilter filter);

    SalesDetailVO findRevenueAndExpenditure(Date startDate, Date endDate);

    ResultMessage redFlush(DocVO docVO);

    DocVO redFlushAndCopy(DocVO target);

    UserVO getCreatorInfo(String creatorID);
}
