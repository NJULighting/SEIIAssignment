package nju.lighting.bl.documentbl;

import nju.lighting.blservice.documentblservice.DocBLService;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.UserVO;
import nju.lighting.vo.doc.historydoc.HistoryDocVO;
import nju.lighting.vo.viewtables.BusinessConditionTableVO;
import nju.lighting.vo.viewtables.SalesDetailItemVO;
import nju.lighting.vo.viewtables.BusinessHistoryItemVO;
import shared.DocumentFilter;
import shared.ResultMessage;
import shared.TwoTuple;

import java.util.Date;
import java.util.List;

/**
 * Created on 2017/12/30.
 * Description
 *
 * @author 陈俊宇
 */
public class DocBLService_Stub implements DocBLService {
    @Override
    public TwoTuple<ResultMessage, String> commitDoc(DocVO doc) {
        return null;
    }

    @Override
    public List<HistoryDocVO> findDocuments(DocumentFilter filter) {
        return null;
    }

    @Override
    public List<SalesDetailItemVO> findSaleRecords(DocumentFilter filter) {
        return null;
    }

    @Override
    public List<BusinessHistoryItemVO> findBusinessHistory(DocumentFilter filter) {
        return null;
    }

    @Override
    public BusinessConditionTableVO findRevenueAndExpenditure(Date startDate, Date endDate) {
        if (startDate.equals(endDate))
            return new BusinessConditionTableVO(111, 112, 113,
                    114, 115, 116, 117,
                    118, 119);
        else
            return new BusinessConditionTableVO(160,213,124,
                    25,624,23,234,
                    643,123);
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
