package nju.lighting.bl.documentbl;

import nju.lighting.blservice.documentblservice.DocBLService;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.doc.alertdoc.AlertDocVO;
import nju.lighting.vo.doc.historydoc.HistoryDocVO;
import nju.lighting.vo.viewtables.BusinessConditionTableVO;
import nju.lighting.vo.viewtables.BusinessHistoryItemVO;
import nju.lighting.vo.viewtables.SalesDetailItemVO;
import shared.DocumentFilter;
import shared.Result;
import shared.ResultMessage;

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
    public Result<DocVO> commitDoc(DocVO doc) {
        return manager.commitDoc(doc);
    }

    @Override
    public List<HistoryDocVO> findDocuments(DocumentFilter filter) {
        return manager.findDocuments(filter);
    }

    @Override
    public List<SalesDetailItemVO> findSaleRecords(DocumentFilter filter) {
        return manager.findSaleRecords(filter);
    }

    @Override
    public List<BusinessHistoryItemVO> findBusinessHistory(DocumentFilter filter) {
        return manager.findBusinessHistory(filter);
    }

    @Override
    public BusinessConditionTableVO findRevenueAndExpenditure(Date startDate, Date endDate) {
        SalesDetailTable table = new SalesDetailTable(startDate, endDate);
        return table.getSalesDetailTable();
    }

    @Override
    public ResultMessage expireAlertDoc(AlertDocVO alertDocVO) {
        return manager.expireAlertDoc(alertDocVO);
    }

    @Override
    public ResultMessage redFlush(DocVO docVO) {
        RedFlush redFlush = new RedFlush();
        return redFlush.redFlush(docVO);
    }

}