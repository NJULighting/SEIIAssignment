package nju.lighting.blservice.documentblservice;

import nju.lighting.vo.DocVO;
import nju.lighting.vo.UserVO;
import nju.lighting.vo.doc.historydoc.HistoryDocVO;
import nju.lighting.vo.viewtables.SalesDetailItemVO;
import nju.lighting.vo.viewtables.BusinessHistoryItemVO;
import nju.lighting.vo.viewtables.BusinessConditionTableVO;
import shared.DocumentFilter;
import shared.ResultMessage;
import shared.TwoTuple;

import java.util.Date;
import java.util.List;

public interface DocBLService {

    /**
     * Commit a document. If commit successfully, it'll return a tuple contains
     * id and <tt>ResultMessage.SUCCESS</tt>. Otherwise, it'll return a tuple
     * contains <tt>ResulMessage.FAILURE</tt> and a null id;
     * @param doc doc to be committed
     * @return <tt>[ID,SUCCESS]</tt> if commit successfully
     */
    TwoTuple<ResultMessage, String> commitDoc(DocVO doc);

    /**
     * Find documents with the constraints the filter defined. The user can only
     * see his own documents with this method. This method should be used when user
     * want to see his commit history, and shouldn't be used in approval module.
     * @param filter constraints for finding documents
     * @return documents that satisfy these constraints
     */
    List<HistoryDocVO> findDocuments(DocumentFilter filter);

    List<SalesDetailItemVO> findSaleRecords(DocumentFilter filter);

    List<BusinessHistoryItemVO> findBusinessHistory(DocumentFilter filter);

    BusinessConditionTableVO findRevenueAndExpenditure(Date startDate, Date endDate);

    ResultMessage redFlush(DocVO docVO);

    DocVO redFlushAndCopy(DocVO target);

    UserVO getCreatorInfo(String creatorID);
}
