package nju.lighting.bl.documentbl;

import nju.lighting.bl.documentbl.table.SalesDetailTable;
import nju.lighting.bl.userbl.UserInfo;
import nju.lighting.bl.userbl.UserInfoImpl;
import nju.lighting.blservice.documentblservice.DocBLService;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.UserVO;
import nju.lighting.vo.doc.historydoc.HistoryDocVO;
import nju.lighting.vo.viewtables.BusinessConditionItemVO;
import nju.lighting.vo.viewtables.BusinessHistoryItemVO;
import nju.lighting.vo.viewtables.SalesDetailVO;
import shared.DocumentFilter;
import shared.ResultMessage;
import shared.TwoTuple;

import java.util.Date;
import java.util.List;

/**
 * Created on 2017/11/7.
 * Description:
 * @author Liao
 */
public class DocController implements DocBLService {
    private DocManager manager = DocManager.INSTANCE;

    @Override
    public TwoTuple<String, ResultMessage> commitDoc(DocVO doc) {
        return manager.commitDoc(doc);
    }

    @Override
    public List<HistoryDocVO> findDocuments(DocumentFilter filter) {
        return manager.findDocuments(filter);
    }

    @Override
    public List<BusinessConditionItemVO> findSaleRecords(DocumentFilter filter) {
        return manager.findSaleRecords(filter);
    }

    @Override
    public List<BusinessHistoryItemVO> findBusinessHistory(DocumentFilter filter) {
        return manager.findBusinessHistory(filter);
    }

    @Override
    public SalesDetailVO findRevenueAndExpenditure(Date startDate, Date endDate) {
        SalesDetailTable table = new SalesDetailTable(startDate, endDate);
        return table.findSalesDetailTable();
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
        UserInfo userInfo = new UserInfoImpl();
        return userInfo.getUserVOByID(creatorID);
    }
}