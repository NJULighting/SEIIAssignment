package nju.lighting.bl.documentbl;

import nju.lighting.blservice.documentblservice.DocBLService;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.UserVO;
import nju.lighting.vo.doc.historydoc.HistoryDocVO;
import nju.lighting.vo.viewtables.BusinessHistoryItemVO;
import nju.lighting.vo.viewtables.SalesDetailVO;
import nju.lighting.vo.viewtables.BusinessConditionItemVO;
import shared.*;

import java.util.Date;
import java.util.List;

/**
 * Created on 2017/11/7.
 * Description:
 * @author Liao
 */
public class DocController implements DocBLService {

    @Override
    public DocVO createDoc(DocType type) {
        return null;
    }

    @Override
    public TwoTuple<String,ResultMessage> commitDoc(DocVO doc) {
        return null;
    }

    @Override
    public List<HistoryDocVO> findDocuments(DocumentFilter filter) {
        return null;
    }

    @Override
    public List<BusinessConditionItemVO> findSaleRecords(BusinessConditionFilter filter) {
        return null;
    }

    @Override
    public List<BusinessHistoryItemVO> findBusinessHistory(DocumentFilter filter) {
        return null;
    }

    @Override
    public SalesDetailVO findRevenueAndExpenditure(Date startDate, Date endDate) {
        return null;
    }

    @Override
    public ResultMessage redFlush(DocVO docVO) {
        return null;
    }

    @Override
    public DocVO redFlushAndCopy(DocVO target) {
        return null;
    }

    @Override
    public UserVO getCreatorInfo(String creatorID) {
        return null;
    }
}